package factory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import entities.EnergyType;
import fileio.DataCons;
import fileio.MonthState;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

@JsonPropertyOrder({"id", "energyNeededKW", "contractPrice", "budget", "producerStrategy",
        "isBankrupt", "contracts"})
public class DistributorOutput implements Output {

    private int id;
    private long budget;
    private boolean isBankrupt;
    private final ArrayList<DataCons> contracts;
    private int energyNeededKW;
    private int contractPrice;
    private EnergyChoiceStrategyType producerStrategy;

    public DistributorOutput(final int id, final long budget, final boolean isBankrupt,
                             final ArrayList<DataCons> contracts, int energyNeededKW,
                             int contractPrice, EnergyChoiceStrategyType producerStrategy) {
        this.id = id;
        this.budget = budget;
        this.isBankrupt = isBankrupt;
        this.contracts = contracts;
        this.energyNeededKW = energyNeededKW;
        this.contractPrice = contractPrice;
        this.producerStrategy = producerStrategy;
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
     * @return energy needed
     */
    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    /**
     * @param energyNeededKW - energy needed
     */
    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    /**
     * @return contract price
     */
    @JsonProperty("contractCost")
    public int getContractPrice() {
        return contractPrice;
    }

    /**
     * @param contractPrice - contract price
     */
    public void setContractPrice(int contractPrice) {
        this.contractPrice = contractPrice;
    }

    /**
     * @return - producer strategy
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
     * @return id of the consumer
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * @param id - distributor id
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
     * @param isBankrupt1 true/false
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
     * @param budget - budget of the distributor
     */
    @Override
    public void setBudget(final long budget) {
        this.budget = budget;
    }

    /**
     * @return contracts - consumers that signed contract
     */
    @Override
    public ArrayList<DataCons> getContracts() {
        return this.contracts;
    }
}
