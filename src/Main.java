import com.fasterxml.jackson.databind.ObjectMapper;
import entities.EnergyType;
import factory.*;
import fileio.*;
import input.MInput;
import strategies.EnergyChoiceStrategyType;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public final class Main {
    private Main() {
    }

    /**
     * @param args - args[0] file to read, args[1] file to write
     * @throws Exception - file not found
     */
    public static void main(final String[] args) throws Exception {

        // read
        File file = new File(args[0]);

        // parse
        ObjectMapper objectMapper = new ObjectMapper();
        Input input = objectMapper.treeToValue(objectMapper.readTree(file), Input.class);

        // copy input with Singleton pattern
        MInput mInput = MInput.getInstance();
        mInput.load(input.getNumberOfTurns(), input.getInitialData(),
                input.getMonthlyUpdates());

        // copy all consumers
        ArrayList<Consumer> allConsumers = new ArrayList<>(mInput.getInitialData().getConsumers());
        // copy all producers
        ArrayList<Producer> allProducers = new ArrayList<>(mInput.getInitialData().getProducers());

        for (int r = 0; r <= mInput.getNumberOfTurns(); r++) {

            // initial round
            if (r == 0) {

                for (int i = 0; i < mInput.getInitialData().getDistributors().size(); i++) {
                    Distributor distributor = mInput.getInitialData().getDistributors().get(i);

                    // set budget
                    distributor.computeInitialBudget();
                    // choose producers and compute initial production cost
                    distributor.getStrategy().choose(distributor, allProducers);
                    distributor.computeProductionCost(allProducers);
                    // make contract
                    distributor.contractPrice();
                }

                // consumers turn
                for (Consumer consumer : allConsumers) {

                    // set budget
                    consumer.computeInitialBudget();
                    // choose contract
                    consumer.chooseContract((ArrayList<Distributor>)
                            mInput.getInitialData().getDistributors());
                }
            }

            // beginning of month

            // check for newConsumers or distributorChanges
            if (r != 0) {
                MonthlyUpdate monthlyUpdate = mInput.getMonthlyUpdates().get(r - 1);

                // new consumers
                if (monthlyUpdate.getNewConsumers() != null) {

                    for (int j = 0; j < monthlyUpdate.getNewConsumers().size(); j++) {
                        Consumer consumer = new Consumer(
                                monthlyUpdate.getNewConsumers().get(j).getId(),
                                monthlyUpdate.getNewConsumers().get(j).getInitialBudget(),
                                monthlyUpdate.getNewConsumers().get(j).getMonthlyIncome());

                        // set budget
                        consumer.computeInitialBudget();
                        consumer.setOnHold(false);  //
                        consumer.setBankrupt(false);  //

                        // add to all others
                        allConsumers.add(consumer);
                    }
                }
                // distributorChanges
                if (monthlyUpdate.getDistributorChanges() != null) {

                    for (int j = 0; j < monthlyUpdate.getDistributorChanges().size(); j++) {
                        DistributorChange distributorChange = monthlyUpdate.getDistributorChanges()
                                .get(j);

                        int index = mInput.getInitialData().findDistrbById(
                                distributorChange.getId());
                        Distributor distributor = mInput.getInitialData()
                                .getDistributors().get(index);

                        distributor.setInitialInfrastructureCost(
                                distributorChange.getInfrastructureCost());
                    }
                }
            }

            // delete bankrupt consumers from their distributors contracts list
            for (Consumer consumer : allConsumers) {
                if (consumer.isBankrupt()) {

                    // identify distributor
                    Distributor distributor = mInput.getInitialData().getDistributors().get(
                            consumer.getChosenContract());

                    for (int j = 0; j < distributor.getContracts().size(); j++) {
                        // delete contract
                        if (distributor.getContracts().get(j).getConsumerId() == consumer.getId()) {
                            distributor.getContracts().remove(j);
                            break;
                        }
                    }
                }
            }
            // clear bankrupt distributors contracts
            for (int i = 0; i < mInput.getInitialData().getDistributors().size(); i++) {
                Distributor distributor = mInput.getInitialData().getDistributors().get(i);

                if (distributor.isBankrupt()) {

                    for (int j = 0; j < distributor.getContracts().size(); j++) {
                        DataCons dataCons = distributor.getContracts().get(j);

                        int index = mInput.getInitialData().findConsById(
                                dataCons.getConsumerId(), allConsumers);
                        Consumer consumer = allConsumers.get(index);

                        consumer.setOnHold(false);
                        consumer.setBankrupt(false);
                        consumer.setRemainedContractMonths(0);
                    }
                    distributor.getContracts().clear();
                }
            }

            // for all other months
            if (r != 0) {
                // recalculate contract price each month
                for (int i = 0; i < mInput.getInitialData().getDistributors().size(); i++) {
                    Distributor distributor = mInput.getInitialData().getDistributors().get(i);

                    if (distributor.isBankrupt()) {
                        continue;
                    }
                    distributor.contractPrice();
                }
            }

            for (Consumer consumer : allConsumers) {
                if (consumer.isBankrupt()) {
                    continue;
                }

                // monthly income
                consumer.addIncome();

                // if consumer doesn't have contract
                if (consumer.getRemainedContractMonths() == 0) {

                    // identify distributor
                    Distributor distributor = mInput.getInitialData().getDistributors().get(
                            consumer.getChosenContract());

                    // delete consumer from distributor contracts list
                    for (int j = 0; j < distributor.getContracts().size(); j++) {

                        if (distributor.getContracts().get(j).getConsumerId() == consumer.getId()) {
                            distributor.getContracts().remove(j);
                            break;
                        }
                    }

                    // choose another contract
                    consumer.chooseContract((ArrayList<Distributor>)
                            mInput.getInitialData().getDistributors());
                }

                // end of month

                // consumers pay
                consumer.payContract();

                // update months on distributors contract list
                int index = mInput.getInitialData().findDistrbById(consumer.getChosenContract());
                Distributor distributor = mInput.getInitialData().getDistributors().get(index);
                for (int j = 0; j < distributor.getContracts().size(); j++) {

                    if (distributor.getContracts().get(j).getConsumerId() == consumer.getId()) {
                        distributor.getContracts().get(j).setRemainedContractMonths(
                                consumer.getRemainedContractMonths());
                    }
                }
            }

            // distributors take money from contracts, then pay expenses
            for (int i = 0; i < mInput.getInitialData().getDistributors().size(); i++) {

                Distributor distributor = mInput.getInitialData().getDistributors().get(i);

                // take money from contracts
                for (int j = 0; j < distributor.getContracts().size(); j++) {
                    DataCons dataCons = distributor.getContracts().get(j);

                    int index = mInput.getInitialData().findConsById(
                            dataCons.getConsumerId(), allConsumers);
                    if (!allConsumers.get(index).isOnHold()) {

                        distributor.setBudget(distributor.getBudget() + dataCons.getPrice());
                    }
                }

                // pay expenses
                distributor.expenses();
            }
            // check for monthly producer changes
            if (r != 0) {
                List<ProducerChange> producerChanges = mInput.getMonthlyUpdates()
                        .get(r - 1).getProducerChanges();

                // if exists
                if (producerChanges.size() > 0) {

                    for (ProducerChange producerChange : producerChanges) {
                        int energyPerDistributor = producerChange.getEnergyPerDistributor();
                        int id = producerChange.getId();

                        // modify the energy
                        allProducers.get(id).setEnergyPerDistributor(energyPerDistributor);
                        // notify all distributors that this producer changed his energy
                        // by using the observer design pattern
                        allProducers.get(id).setChanged();
                        allProducers.get(id).notifyObservers();
                        allProducers.get(id).clearChanged();
                    }
                }
                // all distributors that were notified will reapply their strategies
                // by using the strategy design pattern
                for (int i = 0; i < mInput.getInitialData().getDistributors().size(); i++) {
                    Distributor distributor = mInput.getInitialData().getDistributors().get(i);

                    if (distributor.isReapplyStrategy() && !distributor.isBankrupt()) {
                        distributor.getStrategy().choose(distributor, allProducers);
                        distributor.computeProductionCost(allProducers);
                    }
                }
            }
            // read monthly stats for producers
            for (Producer producer : allProducers) {
                if (r == 0) {
                    continue;
                }
                producer.getMonthlyStats().add(new MonthState(r));

                for (int i = 0; i < mInput.getInitialData().getDistributors().size(); i++) {
                    Distributor distributor = mInput.getInitialData().getDistributors().get(i);

                    if (distributor.isBankrupt()) {
                        continue;
                    }

                    for (int j = 0; j < distributor.getProducerIds().size(); j++) {
                        if (producer.getId() == distributor.getProducerIds().get(j)) {
                            producer.getMonthlyStats().get(r - 1).getDistributorsIds()
                                    .add(distributor.getId());
                        }
                    }
                }
            }

            // if all distributors are bankrupt end simulation
            int count = 0;
            for (int i = 0; i < mInput.getInitialData().getDistributors().size(); i++) {
                if (mInput.getInitialData().getDistributors().get(i).isBankrupt()) {
                    count++;
                }
            }
            if (count == mInput.getInitialData().getDistributors().size()) {
                break;
            }
        }
        // delete all bankrupt consumers at end of game from distributors contracts list
        for (Consumer consumer : allConsumers) {
            if (consumer.isBankrupt()) {

                Distributor distributor = mInput.getInitialData().getDistributors().get(
                        consumer.getChosenContract());

                for (int j = 0; j < distributor.getContracts().size(); j++) {
                    if (distributor.getContracts().get(j).getConsumerId() == consumer.getId()) {
                        distributor.getContracts().remove(j);
                        break;
                    }
                }
            }
        }
        // write to file using Factory Design Pattern
        EntityFactory entityFactory = new EntityFactory();
        OutputData outputData = new OutputData();

        // consumers
        for (Consumer consumer : allConsumers) {
            ArrayList<DataCons> contracts = null;
            int energyNeededKW = -1;
            int contractPrice = -1;
            EnergyChoiceStrategyType producerStrategy = null;
            int maxDistributors = -1;
            int priceKW = -1;
            EnergyType energyType = null;
            int energyPerDistributor = -1;
            ArrayList<MonthState> monthlyStats = null;
            // up fields are dummy fields

            Output output = entityFactory.getInstance("consumer", consumer.getId(),
                    consumer.getBudget(), consumer.isBankrupt(), contracts, energyNeededKW,
                    contractPrice, producerStrategy, maxDistributors, priceKW, energyType,
                    energyPerDistributor, monthlyStats);

            outputData.getConsumers().add((ConsumerOutput) output);
        }

        // distributors
        for (int i = 0; i < mInput.getInitialData().getDistributors().size(); i++) {
            int maxDistributors = -1;
            double priceKW = -1;
            EnergyType energyType = null;
            int energyPerDistributor = -1;
            ArrayList<MonthState> monthlyStats = null;
            // up fields are dummy fields

            Distributor distributor = mInput.getInitialData().getDistributors().get(i);

            Output output = entityFactory.getInstance("distributor", distributor.getId(),
                    distributor.getBudget(), distributor.isBankrupt(), distributor.getContracts(),
                    distributor.getEnergyNeededKW(), (int) distributor.getContractPrice(),
                    distributor.getProducerStrategy(), maxDistributors, priceKW, energyType,
                    energyPerDistributor, monthlyStats);

            outputData.getDistributors().add((DistributorOutput) output);
        }
        // producers
        for (Producer producer : allProducers) {
            int budget = -1;
            boolean isBankrupt = false;
            ArrayList<DataCons> contracts = null;
            int energyNeededKW = -1;
            int contractPrice = -1;
            EnergyChoiceStrategyType producerStrategy = null;
            // up fields are dummy fields

            Output output = entityFactory.getInstance("producer", producer.getId(),
                    budget, isBankrupt, contracts, energyNeededKW, contractPrice, producerStrategy,
                    producer.getMaxDistributors(), producer.getPriceKW(), producer.getEnergyType(),
                    producer.getEnergyPerDistributor(), producer.getMonthlyStats());

            outputData.getEnergyProducers().add((ProducerOutput) output);
        }
        // write to file using the jackson library
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(args[1]), outputData);
    }
}
