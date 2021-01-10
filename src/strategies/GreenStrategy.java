package strategies;

import fileio.Distributor;
import fileio.Producer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

// Strategy Design Pattern
public class GreenStrategy implements IStrategy, Comparator<Producer> {
    /**
     * @param distributor  - chooses its producers
     * @param allProducers - list with all the producers
     */
    @Override
    public void choose(Distributor distributor, ArrayList<Producer> allProducers) {

        if (distributor.isBankrupt()) {
            return;
        }

        // copy in order to not disturb the original one
        ArrayList<Producer> copy = new ArrayList<>(allProducers);
        Collections.sort(copy, this);

        int copyEnergy = distributor.getEnergyNeededKW();

        // sort the distributor's producer ids by ascending id
        Collections.sort(distributor.getProducerIds(), (o1, o2) -> {
            if (o1 < o2) {
                return -1;
            } else if (o1 > o2) {
                return 1;
            }
            return 0;
        });
        // sort all producers by ascending id
        Collections.sort(allProducers, (o1, o2) -> {
            if (o1.getId() < o2.getId()) {
                return -1;
            } else if (o1.getId() > o2.getId()) {
                return 1;
            }
            return 0;
        });

        // distributor removes itself from all producers lists
        for (int i = 0; i < distributor.getProducerIds().size(); i++) {
            int id = distributor.getProducerIds().get(i);

            Producer producer = allProducers.get(id);
            producer.getDistributorsIds().remove((Integer) distributor.getId());
            producer.deleteObserver(distributor);

        }
        // clear the current producer ids list
        distributor.getProducerIds().clear();

        for (Producer producer : copy) {
            if (copyEnergy <= 0) {
                break;
            }

            if (producer.getDistributorsIds().size() == producer.getMaxDistributors()) {
                continue;
            }

            int producerEnergy = producer.getEnergyPerDistributor();
            int producerId = producer.getId();
            copyEnergy = copyEnergy - producerEnergy;
            distributor.getProducerIds().add(producerId);

            // Observer Design Pattern to add the new observers
            producer.addObserver(distributor);
            distributor.setReapplyStrategy(false);

            // update each producer's list
            producer.getDistributorsIds().add(distributor.getId());
        }
    }

    /**
     * compare function
     *
     * @param o1 - first producer
     * @param o2 - second producer
     * @return comparing result
     */
    // Green Strategy comparator
    @Override
    public int compare(Producer o1, Producer o2) {
        int diff = Boolean.compare(o2.getEnergyType().isRenewable(),
                o1.getEnergyType().isRenewable());
        if (diff != 0) {
            return diff;
        } else {
            if (o1.getPriceKW() < o2.getPriceKW()) {
                return -1;
            } else if (o1.getPriceKW() > o2.getPriceKW()) {
                return 1;
            } else {
                if (o1.getEnergyPerDistributor() >= o2.getEnergyPerDistributor()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }
}
