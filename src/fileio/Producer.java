package fileio;

import entities.EnergyType;

import java.util.ArrayList;
import java.util.Observable;

public class Producer extends Observable {

    private int id;
    private EnergyType energyType;
    private int maxDistributors;
    private double priceKW;
    private int energyPerDistributor;
    private ArrayList<MonthState> monthlyStats = new ArrayList<>();
    private ArrayList<Integer> distributorsIds = new ArrayList<>();

    /**
     * @return distributors ids that take energy from this producer
     */
    public ArrayList<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    /**
     * @param distributorsIds - distributors ids that take energy from this producer
     */
    public void setDistributorsIds(ArrayList<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }

    /**
     * Observer Design Pattern
     * <p>
     * Marks this {@code Observable} object as having been changed; the
     * {@code hasChanged} method will now return {@code true}.
     */
    @Override
    public synchronized void setChanged() {
        super.setChanged();
    }

    /**
     * Observer Design Pattern
     * <p>
     * Indicates that this object has no longer changed, or that it has
     * already notified all of its observers of its most recent change,
     * so that the {@code hasChanged} method will now return {@code false}.
     * This method is called automatically by the
     * {@code notifyObservers} methods.
     *
     * @see Observable#notifyObservers()
     * @see Observable#notifyObservers(Object)
     */
    @Override
    public synchronized void clearChanged() {
        super.clearChanged();
    }

    /**
     * @return monthly stats
     */
    public ArrayList<MonthState> getMonthlyStats() {
        return monthlyStats;
    }

    /**
     * @param monthlyStats - monthly stats
     */
    public void setMonthlyStats(ArrayList<MonthState> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

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
     * @return energy type
     */
    public EnergyType getEnergyType() {
        return energyType;
    }

    /**
     * @param energyType - energy type
     */
    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    /**
     * @return max distributors
     */
    public int getMaxDistributors() {
        return maxDistributors;
    }

    /**
     * @param maxDistributors - max distributors
     */
    public void setMaxDistributors(int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    /**
     * @return price kw
     */
    public double getPriceKW() {
        return priceKW;
    }

    /**
     * @param priceKW - price kw
     */
    public void setPriceKW(double priceKW) {
        this.priceKW = priceKW;
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
