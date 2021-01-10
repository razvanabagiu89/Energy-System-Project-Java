package fileio;

import java.util.ArrayList;
import java.util.List;

public class InitialData {

    private List<Consumer> consumers;
    private List<Distributor> distributors;
    private List<Producer> producers;

    /**
     * @param id           - identifier
     * @param allConsumers - consumers
     * @return index of consumer
     */
    public int findConsById(final int id, final ArrayList<Consumer> allConsumers) {

        for (int i = 0; i < allConsumers.size(); i++) {
            if (allConsumers.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param id - identifier
     * @return index of distributor
     */
    public int findDistrbById(final int id) {

        for (int i = 0; i < distributors.size(); i++) {
            if (distributors.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @return producers
     */
    public List<Producer> getProducers() {
        return producers;
    }

    /**
     * @param producers - producers
     */
    public void setProducers(List<Producer> producers) {
        this.producers = producers;
    }

    /**
     * @return consumers
     */
    public List<Consumer> getConsumers() {
        return consumers;
    }

    /**
     * @param consumers - consumers
     */
    public void setConsumers(final List<Consumer> consumers) {
        this.consumers = consumers;
    }

    /**
     * @return distributors
     */
    public List<Distributor> getDistributors() {
        return distributors;
    }

    /**
     * @param distributors - distributors
     */
    public void setDistributors(final List<Distributor> distributors) {
        this.distributors = distributors;
    }
}
