package fileio;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"consumerId", "price", "remainedContractMonths"})
public class DataCons {

    private int consumerId;
    private long price;
    private int remainedContractMonths;

    @JsonCreator
    public DataCons(@JsonProperty("consumerId") final int consumerId,
                    @JsonProperty("price") final long price,
                    @JsonProperty("remainedContractMonths") final int remainedContractMonths) {
        this.consumerId = consumerId;
        this.price = price;
        this.remainedContractMonths = remainedContractMonths;
    }

    /**
     * @return id
     */
    public int getConsumerId() {
        return consumerId;
    }

    /**
     * @param consumerId - identifier
     */
    public void setConsumerId(final int consumerId) {
        this.consumerId = consumerId;
    }

    /**
     * @return price
     */
    public long getPrice() {
        return price;
    }

    /**
     * @param price - contract price
     */
    public void setPrice(final int price) {
        this.price = price;
    }

    /**
     * @return remaining months
     */
    public int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    /**
     * @param remainedContractMonths - remaining months
     */
    public void setRemainedContractMonths(final int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }
}
