package fileio;

public class DistributorChange {

    private int id;
    private int infrastructureCost;

    /**
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id - identifier
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * @return infrastructure cost
     */
    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    /**
     * @param infrastructureCost - infrastructure cost
     */
    public void setInfrastructureCost(final int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }
}
