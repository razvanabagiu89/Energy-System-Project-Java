package strategies;

import fileio.Distributor;
import fileio.Producer;

import java.util.ArrayList;

// Strategy Design Pattern
public interface IStrategy {
    /**
     * @param distributor  - chooses its producers
     * @param allProducers - list with all the producers
     */
    void choose(Distributor distributor, ArrayList<Producer> allProducers);

    /**
     * compare function
     *
     * @param o1 - first producer
     * @param o2 - second producer
     * @return comparing result
     */
    int compare(Producer o1, Producer o2);
}
