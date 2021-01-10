package fileio;

public class ProducerChange {
    private int id;
    private int energyPerDistributor;

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id - id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return energy per distributor
     */
    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    /**
     * @param energyPerDistributor - energy per distributor
     */
    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }
}
