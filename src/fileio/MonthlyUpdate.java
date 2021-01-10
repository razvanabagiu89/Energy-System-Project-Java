package fileio;

import java.util.List;

public class MonthlyUpdate {

    private List<NewConsumer> newConsumers;
    private List<DistributorChange> distributorChanges;
    private List<ProducerChange> producerChanges;

    /**
     * @return producer changes
     */
    public List<ProducerChange> getProducerChanges() {
        return producerChanges;
    }

    /**
     * @param producerChanges - producer changes
     */
    public void setProducerChanges(List<ProducerChange> producerChanges) {
        this.producerChanges = producerChanges;
    }

    /**
     * @return new consumers
     */
    public List<NewConsumer> getNewConsumers() {
        return newConsumers;
    }

    /**
     * @param newConsumers - new consumers
     */
    public void setNewConsumers(final List<NewConsumer> newConsumers) {
        this.newConsumers = newConsumers;
    }

    /**
     * @return distributor changes
     */
    public List<DistributorChange> getDistributorChanges() {
        return distributorChanges;
    }

    /**
     * @param distributorChanges - distributor changes
     */
    public void setDistributorChanges(final List<DistributorChange> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }
}
