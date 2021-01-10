package factory;

import entities.EnergyType;
import fileio.DataCons;
import fileio.MonthState;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

public class EntityFactory {
    /**
     * Factory Design Pattern
     *
     * @param type                 - consumer/distributor/producer
     * @param id                   - each type has an id
     * @param budget               - budget
     * @param isBankrupt           - checks if type is bankrupt
     * @param contracts            - consumers that signed contract with distributor
     * @param energyNeededKW       - energy needed by a distributor
     * @param contractPrice        - contract price
     * @param producerStrategy     - distributor strategy to choose producers
     * @param maxDistributors      - max distributors a producer can have
     * @param priceKW              - price per kw on producer
     * @param energyType           - type of energy - renewable or not
     * @param energyPerDistributor - energy a producer can give to a distributor monthly
     * @param monthlyStats         - monthly statistics for producer
     * @return new instance of type
     */
    public Output getInstance(final String type, final int id, final long budget,
                              final boolean isBankrupt, final ArrayList<DataCons> contracts,
                              final int energyNeededKW, final int contractPrice,
                              final EnergyChoiceStrategyType producerStrategy,
                              final int maxDistributors, final double priceKW,
                              final EnergyType energyType, final int energyPerDistributor,
                              final ArrayList<MonthState> monthlyStats) {

        return switch (type) {
            case "consumer" -> new ConsumerOutput(id, budget, isBankrupt);
            case "distributor" -> new DistributorOutput(id, budget, isBankrupt, contracts,
                    energyNeededKW,
                    contractPrice, producerStrategy);
            case "producer" -> new ProducerOutput(id, maxDistributors, priceKW,
                    energyType, energyPerDistributor, monthlyStats);
            default -> null;
        };
    }
}
