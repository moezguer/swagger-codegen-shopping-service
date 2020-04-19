package io.swagger.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import org.threeten.bp.OffsetDateTime;

import javax.validation.Valid;
import java.util.Objects;
import java.util.UUID;

/**
 * OrderResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
                            date = "2020-03-27T16:32:59.829Z[GMT]")
public class OrderResponse {
    @JsonProperty("id")
    private UUID id = null;

    @JsonProperty("dateTime")
    private OffsetDateTime dateTime = null;

    @JsonProperty("orderItems")
    private CartResponse orderItems = null;

    public OrderResponse id(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @ApiModelProperty(value = "")

    @Valid public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public OrderResponse dateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    /**
     * Get dateTime
     *
     * @return dateTime
     **/
    @ApiModelProperty(value = "")

    @Valid public OffsetDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(OffsetDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public OrderResponse orderItems(CartResponse orderItems) {
        this.orderItems = orderItems;
        return this;
    }

    /**
     * Get orderItems
     *
     * @return orderItems
     **/
    @ApiModelProperty(value = "")

    @Valid public CartResponse getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(CartResponse orderItems) {
        this.orderItems = orderItems;
    }


    @Override public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrderResponse orderResponse = (OrderResponse) o;
        return Objects.equals(this.id, orderResponse.id) && Objects.equals(this.dateTime, orderResponse.dateTime) &&
               Objects.equals(this.orderItems, orderResponse.orderItems);
    }

    @Override public int hashCode() {
        return Objects.hash(id, dateTime, orderItems);
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class OrderResponse {\n");

        sb.append("    id: ")
          .append(toIndentedString(id))
          .append("\n");
        sb.append("    dateTime: ")
          .append(toIndentedString(dateTime))
          .append("\n");
        sb.append("    orderItems: ")
          .append(toIndentedString(orderItems))
          .append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString()
                .replace("\n", "\n    ");
    }
}
