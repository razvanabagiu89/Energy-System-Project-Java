package fileio;

public class NewConsumer {

    private int id;
    private int initialBudget;
    private int monthlyIncome;

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
     * @return initial budget
     */
    public int getInitialBudget() {
        return initialBudget;
    }

    /**
     * @param initialBudget - initial budget
     */
    public void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }

    /**
     * @return monthly income
     */
    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     * @param monthlyIncome - monthly income
     */
    public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }
}
