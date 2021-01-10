package factory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import entities.EnergyType;
import fileio.DataCons;
import fileio.MonthState;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

@JsonPropertyOrder({"id", "isBankrupt", "budget"})
public class ConsumerOutput implements Output {

    private int id;
    private long budget;
    private boolean isBankrupt;

    public ConsumerOutput(final int id, final long budget, final boolean isBankrupt) {
        this.id = id;
        this.budget = budget;
        this.isBankrupt = isBankrupt;
    }

    /**
     * @param monthlyStats - monthly stats
     */
    @Override
    @JsonProperty
    public void setMonthlyStats(ArrayList<MonthState> monthlyStats) {
    }

    /**
     * @return monthly stats
     */
    @Override
    @JsonIgnore
    public ArrayList<MonthState> getMonthlyStats() {
        return null;
    }

    /**
     * @param energyPerDistributor - energy per distributor
     */
    @Override
    @JsonProperty
    public void setEnergyPerDistributor(int energyPerDistributor) {
    }

    /**
     * @return energy per distributor
     */
    @Override
    @JsonIgnore
    public int getEnergyPerDistributor() {
        return 0;
    }

    /**
     * @param energyType - energy Type
     */
    @Override
    @JsonProperty
    public void setEnergyType(EnergyType energyType) {
    }

    /**
     * @return energy type
     */
    @Override
    @JsonIgnore
    public EnergyType getEnergyType() {
        return null;
    }

    /**
     * @param priceKW - price kw
     */
    @Override
    @JsonProperty
    public void setPriceKW(double priceKW) {
    }

    /**
     * @return price kw
     */
    @Override
    @JsonIgnore
    public double getPriceKW() {
        return 0;
    }

    /**
     * @param maxDistributors - max distributors
     */
    @Override
    @JsonProperty
    public void setMaxDistributors(int maxDistributors) {
    }

    /**
     * @return max distributors
     */
    @Override
    @JsonIgnore
    public int getMaxDistributors() {
        return 0;
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
     * @return energy needed
     */
    @Override
    @JsonIgnore
    public int getEnergyNeededKW() {
        return 0;
    }

    /**
     * @param energyNeededKW - energy needed
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
     * @param id - id of the consumer
     */
    @Override
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @return isBankrupt
     */
    @Override
    public boolean isBankrupt() {
        return isBankrupt;
    }

    /**
     * @param isBankrupt1 - true/false
     */
    @Override
    @JsonProperty("isBankrupt")
    public void setBankrupt(final boolean isBankrupt1) {
        this.isBankrupt = isBankrupt1;
    }

    /**
     * @return budget
     */
    @Override
    public long getBudget() {
        return budget;
    }

    /**
     * @param budget - budget of the consumer
     */
    @Override
    public void setBudget(final long budget) {
        this.budget = budget;
    }

    /**
     * @return null / consumers don't have contracts
     */
    @Override
    @JsonIgnore
    public ArrayList<DataCons> getContracts() {
        return null;
    }
}
