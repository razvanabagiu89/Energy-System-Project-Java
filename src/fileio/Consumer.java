package fileio;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;

@JsonPropertyOrder({"id", "isBankrupt", "budget"})
public class Consumer {

    private int id;
    private int initialBudget;
    private int monthlyIncome;
    private boolean isBankrupt = false;
    private long budget;
    private boolean onHold = false;
    private long price;
    private int remainedContractMonths = 0;
    private int chosenContract;
    private int prevContract;
    private Distributor prevDistrb;
    private Distributor currDistrb;

    @JsonCreator
    public Consumer(@JsonProperty("id") final int id,
                    @JsonProperty("initialBudget") final int initialBudget,
                    @JsonProperty("monthlyIncome") final int monthlyIncome) {
        this.id = id;
        this.initialBudget = initialBudget;
        this.monthlyIncome = monthlyIncome;
    }

    /**
     * @param chosenContract - chosen contract
     */
    public void setChosenContract(int chosenContract) {
        this.chosenContract = chosenContract;
    }

    /**
     * @return previous contract
     */
    public int getPrevContract() {
        return prevContract;
    }

    /**
     * @param prevContract - previous contract
     */
    public void setPrevContract(int prevContract) {
        this.prevContract = prevContract;
    }

    /**
     * @return previous distributor
     */
    public Distributor getPrevDistrb() {
        return prevDistrb;
    }

    /**
     * @param prevDistrb - previous distributor
     */
    public void setPrevDistrb(Distributor prevDistrb) {
        this.prevDistrb = prevDistrb;
    }

    /**
     * @return current distributor
     */
    public Distributor getCurrDistrb() {
        return currDistrb;
    }

    /**
     * @param currDistrb - current distributor
     */
    public void setCurrDistrb(Distributor currDistrb) {
        this.currDistrb = currDistrb;
    }

    /**
     * @return signed contract id
     */
    @JsonIgnore
    public int getChosenContract() {
        return chosenContract;
    }

    /**
     * pay the contract in price field
     */
    public void payContract() {

        // if consumer is not in debt
        if (!isOnHold()) {
            if (budget - price >= 0) {
                budget = budget - price;
            } else {
                setOnHold(true);
            }
        } else {
            // same distributor
            if (prevDistrb != null) {
                if (prevDistrb.equals(currDistrb)) {
                    budget = budget - Math.round(Math.floor(1.2 * prevContract)) - chosenContract;
                } else if (!prevDistrb.equals(currDistrb)) { // different distributors
                    budget = budget - Math.round(Math.floor(prevContract));
                    setOnHold(true);
                }
            } else {   // if he can't pay at all
                setBankrupt(true);
            }
        }
        setRemainedContractMonths(getRemainedContractMonths() - 1);
    }

    /**
     * choose contract from distributors list
     *
     * @param distributors - distributors
     */
    public void chooseContract(final ArrayList<Distributor> distributors) {

        this.prevContract = chosenContract;
        this.prevDistrb = this.currDistrb;
        long min = Integer.MAX_VALUE;
        int index = -1;

        for (Distributor value : distributors) {

            if (value.isBankrupt()) {
                continue;
            }
            if (min > value.getContractPrice()) {
                min = value.getContractPrice();
                index = value.getId();
            }
        }

        for (Distributor value : distributors) {

            if (value.getId() == index) {

                this.currDistrb = value;
                this.chosenContract = value.getId();
                this.price = value.getContractPrice();
                this.remainedContractMonths = value.getContractLength();
                value.getContracts().add(new DataCons(this.id, this.price,
                        this.remainedContractMonths));
                break;
            }
        }
    }

    /**
     * add monthly income
     */
    public void addIncome() {
        this.budget += this.monthlyIncome;
    }

    /**
     * initialize budget
     */
    public void computeInitialBudget() {
        this.budget = this.initialBudget;
        this.setRemainedContractMonths(0);
    }

    /**
     * @return price
     */
    @JsonIgnore
    public long getPrice() {
        return price;
    }

    /**
     * @param price - price of contract
     */
    @JsonProperty
    public void setPrice(final long price) {
        this.price = price;
    }

    /**
     * @return remaining months
     */
    @JsonIgnore
    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    /**
     * @param remainedContractMonths - remaining months
     */
    @JsonProperty
    public void setRemainedContractMonths(final int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }

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
    @JsonIgnore
    public int getInitialBudget() {
        return initialBudget;
    }

    /**
     * @param initialBudget - initial budget
     */
    @JsonProperty
    public void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
    }

    /**
     * @return monthly income
     */
    @JsonIgnore
    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    /**
     * @param monthlyIncome - monthly income
     */
    @JsonProperty
    public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    /**
     * @return isBankrupt
     */
    public boolean isBankrupt() {
        return isBankrupt;
    }

    /**
     * @param isBankrupt1 - true/false
     */
    @JsonProperty("isBankrupt")
    public void setBankrupt(final boolean isBankrupt1) {
        this.isBankrupt = isBankrupt1;
    }

    /**
     * @return budget
     */
    public long getBudget() {
        return budget;
    }

    /**
     * @param budget - budget
     */
    public void setBudget(final long budget) {
        this.budget = budget;
    }

    /**
     * @return - if user is in debt
     */
    @JsonIgnore
    public boolean isOnHold() {
        return onHold;
    }

    /**
     * @param onHold - onHold
     */
    @JsonProperty
    public void setOnHold(final boolean onHold) {
        this.onHold = onHold;
    }
}
