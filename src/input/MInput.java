package input;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fileio.InitialData;
import fileio.MonthlyUpdate;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties
public final class MInput {

    private int numberOfTurns;
    private InitialData initialData;
    private List<MonthlyUpdate> monthlyUpdates;

    // Singleton Design Pattern - eager instantiation
    static MInput mInput = new MInput();

    private MInput() {
    }

    /**
     * @param numberOfTurns1  - number of rounds
     * @param initialData1    - initial data
     * @param monthlyUpdates1 - monthly updates
     * @return loaded input
     */
    public MInput load(final int numberOfTurns1, final InitialData initialData1,
                       final List<MonthlyUpdate> monthlyUpdates1) {
        this.numberOfTurns = numberOfTurns1;
        this.initialData = initialData1;
        this.monthlyUpdates = new ArrayList<>(monthlyUpdates1);

        return mInput;
    }

    /**
     * @return singleton instance
     */
    public static MInput getInstance() {
        return mInput;
    }

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
