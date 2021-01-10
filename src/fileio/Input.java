package fileio;

import java.util.List;

public class Input {

    private int numberOfTurns;
    private InitialData initialData;
    private List<MonthlyUpdate> monthlyUpdates;

    /**
     * @return number of rounds
     */
    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    /**
     * @param numberOfTurns - number of rounds
     */
    public void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    /**
     * @return initial data
     */
    public InitialData getInitialData() {
        return initialData;
    }

    /**
     * @param initialData - initial data
     */
    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    /**
     * @return monthly updates
     */
    public List<MonthlyUpdate> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    /**
     * @param monthlyUpdates - monthly updates
     */
    public void setMonthlyUpdates(final List<MonthlyUpdate> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }
}
