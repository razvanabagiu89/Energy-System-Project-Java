package factory;

import entities.EnergyType;
import fileio.DataCons;
import fileio.MonthState;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

public interface Output {
    /**
     * @param monthlyStats - monthly stats
     */
    void setMonthlyStats(ArrayList<MonthState> monthlyStats);

    /**
     * @return monthly stats
     */
    ArrayList<MonthState> getMonthlyStats();

    /**
     * @param energyPerDistributor - energy per distributor
     */
    void setEnergyPerDistributor(int energyPerDistributor);

    /**
     * @return energy per distributor
     */
    int getEnergyPerDistributor();

    /**
     * @param energyType - energy type
     */
    void setEnergyType(EnergyType energyType);

    /**
     * @return energy type
     */
    EnergyType getEnergyType();

    /**
     * @param priceKW - price kw
     */
    void setPriceKW(double priceKW);

    /**
     * @return price kw
     */
    double getPriceKW();

    /**
     * @param maxDistributors - max distributors
     */
    void setMaxDistributors(int maxDistributors);

    /**
     * @return max distributors
     */
    int getMaxDistributors();

    /**
     * @param producerStrategy - producer strategy
     */
    void setProducerStrategy(EnergyChoiceStrategyType producerStrategy);

    /**
     * @return producer strategy
     */
    EnergyChoiceStrategyType getProducerStrategy();

    /**
     * @param contractPrice - contract price
     */
    void setContractPrice(int contractPrice);

    /**
     * @return contract price
     */
    int getContractPrice();

    /**
     * @return energyNeededKW
     */
    int getEnergyNeededKW();

    /**
     * @param energyNeededKW energyNeededKW
     */
    void setEnergyNeededKW(int energyNeededKW);

    /**
     * @return id
     */
    int getId();

    /**
     * @param id - identifier
     */
    void setId(int id);

    /**
     * @return isBankrupt
     */
    boolean isBankrupt();

    /**
     * @param isBankrupt1 - true/false
     */
    void setBankrupt(boolean isBankrupt1);

    /**
     * @return budget
     */
    long getBudget();

    /**
     * @param budget - budget
     */
    void setBudget(long budget);

    /**
     * @return contracts
     */
    ArrayList<DataCons> getContracts();
}
