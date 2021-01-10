package factory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import entities.EnergyType;
import fileio.DataCons;
import fileio.MonthState;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

@JsonPropertyOrder({"id", "maxDistributors", "priceKW", "energyType", "energyPerDistributor",
        "monthlyStats"})
public class ProducerOutput implements Output {

    private final int id;
    private final int maxDistributors;
    private final double priceKW;
    private final EnergyType energyType;
    private final int energyPerDistributor;
    private final ArrayList<MonthState> monthlyStats;

    public ProducerOutput(int id, int maxDistributors, double priceKW,
                          EnergyType energyType, int energyPerDistributor,
                          ArrayList<MonthState> monthlyStats) {
        this.id = id;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyType = energyType;
        this.energyPerDistributor = energyPerDistributor;
        this.monthlyStats = monthlyStats;
    }

    /**
     * @param monthlyStats - monthly stats
     */
    @Override
    public void setMonthlyStats(ArrayList<MonthState> monthlyStats) {

    }

    /**
     * @return monthly stats
     */
    @Override
    public ArrayList<MonthState> getMonthlyStats() {
        return monthlyStats;
    }

    /**
     * @param energyPerDistributor - energy per distributor
     */
    @Override
    public void setEnergyPerDistributor(int energyPerDistributor) {

    }

    /**
     * @return energy per distributor
     */
    @Override
    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    /**
     * @param energyType - energy type
     */
    @Override
    public void setEnergyType(EnergyType energyType) {

    }

    /**
     * @return energy type
     */
    @Override
    public EnergyType getEnergyType() {
        return energyType;
    }

    /**
     * @param priceKW - price kw
     */
    @Override
    public void setPriceKW(double priceKW) {

    }

    /**
     * @return price kw
     */
    @Override
    public double getPriceKW() {
        return priceKW;
    }

    /**
     * @param maxDistributors - max distributors
     */
    @Override
    public void setMaxDistributors(int maxDistributors) {

    }

    /**
     * @return max distributors
     */
    @Override
    public int getMaxDistributors() {
        return maxDistributors;
    }

    /**
     * @param producerStrategy - producer strategy
     */
    @Override
    @JsonProperty
    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {

    }

    /**
     * @return producer strategy
     */
    @Override
    @JsonIgnore
    public EnergyChoiceStrategyType getProducerStrategy() {
        return null;
    }

    /**
     * @param contractPrice - contract price
     */
    @Override
    @JsonProperty
    public void setContractPrice(int contractPrice) {

    }

    /**
     * @return contract price
     */
    @Override
    @JsonIgnore
    public int getContractPrice() {
        return 0;
    }

    /**
     * @return energy needed kw
     */
    @Override
    @JsonIgnore
    public int getEnergyNeededKW() {
        return 0;
    }

    /**
     * @param energyNeededKW energy needed kw
     */
    @Override
    @JsonProperty
    public void setEnergyNeededKW(int energyNeededKW) {

    }

    /**
     * @return id
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * @param id - identifier
     */
    @Override
    public void setId(int id) {

    }

    /**
     * @return isBankrupt
     */
    @Override
    @JsonIgnore
    public boolean isBankrupt() {
        return false;
    }

    /**
     * @param isBankrupt1 - true/false
     */
    @Override
    @JsonProperty
    public void setBankrupt(boolean isBankrupt1) {

    }

    /**
     * @return budget
     */
    @Override
    @JsonIgnore
    public long getBudget() {
        return 0;
    }

    /**
     * @param budget - budget
     */
    @Override
    @JsonProperty
    public void setBudget(long budget) {

    }

    /**
     * @return contracts
     */
    @Override
    @JsonIgnore
    public ArrayList<DataCons> getContracts() {
        return null;
    }
}
