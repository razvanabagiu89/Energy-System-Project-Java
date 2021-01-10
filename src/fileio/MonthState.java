package fileio;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;

@JsonPropertyOrder({"month", "distributorsIds"})
public class MonthState {

    private int month;
    private ArrayList<Integer> distributorsIds;

    public MonthState(int month) {
        this.month = month;
        this.distributorsIds = new ArrayList<>();
    }

    /**
     * @return month number
     */
    public int getMonth() {
        return month;
    }

    /**
     * @param month - month number
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return - distributors ids
     */
    public ArrayList<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    /**
     * @param distributorsIds - distributors ids
     */
    public void setDistributorsIds(ArrayList<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }
}
