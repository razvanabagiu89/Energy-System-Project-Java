package factory;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;

@JsonPropertyOrder({"consumers", "distributors", "energyProducers"})
public class OutputData {

    private final ArrayList<ConsumerOutput> consumers = new ArrayList<>();
    private final ArrayList<DistributorOutput> distributors = new ArrayList<>();
    private final ArrayList<ProducerOutput> energyProducers = new ArrayList<>();

    /**
     * @return final producers
     */
    public ArrayList<ProducerOutput> getEnergyProducers() {
        return energyProducers;
    }

    /**
     * @return final consumers
     */
    public ArrayList<ConsumerOutput> getConsumers() {
        return consumers;
    }

    /**
     * @return final distributors
     */
    public ArrayList<DistributorOutput> getDistributors() {
        return distributors;
    }
}
