package fileio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import strategies.*;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

@JsonPropertyOrder({"id", "energyNeededKW", "contractPrice", "budget", "producerStrategy",
        "isBankrupt", "contracts"})
public class Distributor implements Observer {

    static final double PERCENTAGE = 0.2;
    private int id;
    private int contractLength;
    private int initialBudget;
    private int initialInfrastructureCost;
    private int initialProductionCost;
    private final ArrayList<DataCons> contracts = new ArrayList<>();
    private boolean isBankrupt = false;
    private long budget;
    private long contractPrice;
    private int energyNeededKW;
    private EnergyChoiceStrategyType producerStrategy;
    private IStrategy strategy;
    private ArrayList<Integer> producerIds = new ArrayList<>();
    private boolean reapplyStrategy = false;

    /**
     * @return flag for choosing producers
     */
    @JsonIgnore
    public boolean isReapplyStrategy() {
        return reapplyStrategy;
    }

    /**
     * @param reapplyStrategy - flag for choosing producers
     */
    @JsonProperty
    public void setReapplyStrategy(boolean reapplyStrategy) {
        this.reapplyStrategy = reapplyStrategy;
    }

    /**
     * Observer Design Pattern
     * <p>
     * on notify() call from observable (producer) it updates the flag
     * in order to reapply the choosing strategy
     */
    @Override
    public void update(Observable o, Object arg) {
        this.reapplyStrategy = true;
    }

    /**
     * @param allProducers - list with all producers
     */
    public void computeProductionCost(ArrayList<Producer> allProducers) {

        int cost = 0;
        for (Integer producerId : producerIds) {

            Producer producer = allProducers.get(producerId);
            cost += producer.getEnergyPerDistributor() * producer.getPriceKW();
        }
        this.initialProductionCost = (int) Math.round(Math.floor(cost / 10));
    }

    /**
     * @return producer ids
     */
    public ArrayList<Integer> getProducerIds() {
        return producerIds;
    }

    /**
     * @param producerIds producer ids
     */
    public void setProducerIds(ArrayList<Integer> producerIds) {
        this.producerIds = producerIds;
    }

    /**
     * @return strategy
     */
    @JsonIgnore
    public IStrategy getStrategy() {
        return strategy;
    }

    /**
     * set the distributor strategy
     */
    @JsonProperty
    public void setStrategy() {
        switch (this.producerStrategy.label) {
            case "GREEN" -> this.strategy = new GreenStrategy();
            case "PRICE" -> this.strategy = new PriceStrategy();
            case "QUANTITY" -> this.strategy = new QuantityStrategy();
            default -> this.strategy = null;
        }
    }

    /**
     * initialize budget and set initial strategy
     */
    public void computeInitialBudget() {
        this.budget = this.initialBudget;
        // set strategy on distributor initiation
        setStrategy();
    }

    /**
     * @return contract price
     */
    @JsonProperty("contractCost")
    public long getContractPrice() {
        return contractPrice;
    }

    /**
     * calculates expenses to be paid at end of month
     */
    public void expenses() {
        int expenses;

        if (isBankrupt) {
            return;
        } else {
            if (contracts.size() == 0) {
                expenses = getInitialInfrastructureCost();
            } else {
                expenses = getInitialInfrastructureCost() + getInitialProductionCost()
                        * contracts.size();
            }
            if (this.budget - expenses < 0) {
                isBankrupt = true;
            }
        }
        this.budget = this.budget - expenses;
    }

    /**
     * @return contract price
     */
    public long contractPrice() {
        long price;

        if (contracts.size() == 0) {
            price = getInitialInfrastructureCost() + getInitialProductionCost() + getProfit();
        } else {
            price = Math.round(Math.floor(getInitialInfrastructureCost() / contracts.size())
                    + getInitialProductionCost() + getProfit());
        }
        this.contractPrice = price;
        return price;
    }

    /**
     * @return energy needed kw
     */
    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    /**
     * @param energyNeededKW - energy needed kw
     */
    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    /**
     * @return producer strategy
     */
    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    /**
     * @param producerStrategy - producer strategy
     */
    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    /**
     * @return profit
     */
    @JsonIgnore
    public int getProfit() {
        int profit;
        profit = (int) Math.round(Math.floor(PERCENTAGE * getInitialProductionCost()));
        return profit;
    }

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id - identifier
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @return - contract length
     */
    @JsonIgnore
    public int getContractLength() {
        return contractLength;
    }

    /**
     * @param contractLength - contract length
     */
    @JsonProperty
    public void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    /**
     * @return - initial budget
     */
    @JsonIgnore
    public int getInitialBudget() {
        return initialBudget;
    }

    /**
     * @param initialBudget - initial budget
     */
    @JsonProperty
    public void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }

    /**
     * @return - infrastructure cost
     */
    @JsonIgnore
    public int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    /**
     * @param initialInfrastructureCost - infrastructure cost
     */
    @JsonProperty
    public void setInitialInfrastructureCost(final int initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
    }

    /**
     * @return production cost
     */
    @JsonIgnore
    public int getInitialProductionCost() {
        return initialProductionCost;
    }

    /**
     * @param initialProductionCost - production cost
     */
    @JsonProperty
    public void setInitialProductionCost(final int initialProductionCost) {
        this.initialProductionCost = initialProductionCost;
    }

    /**
     * @return contracts
     */
    public ArrayList<DataCons> getContracts() {
        return contracts;
    }

    /**
     * @return isBankrupt
     */
    public boolean isBankrupt() {
        return isBankrupt;
    }

    /**
     * @param isBankrupt1 - true/false
     */
    @JsonProperty("isBankrupt")
    public void setBankrupt(final boolean isBankrupt1) {
        this.isBankrupt = isBankrupt1;
    }

    /**
     * @return budget
     */
    public long getBudget() {
        return budget;
    }

    /**
     * @param budget - distributor's budget
     */
    public void setBudget(final long budget) {
        this.budget = budget;
    }
}
