package io.swagger.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Objects;
import java.util.UUID;

/**
 * ItemResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
                            date = "2020-03-27T16:32:59.829Z[GMT]")
public class ItemResponse {
    @JsonProperty("productId")
    private UUID productId = null;

    @JsonProperty("quantity")
    private Integer quantity = null;

    public ItemResponse productId(UUID productId) {
        this.productId = productId;
        return this;
    }

    /**
     * Get productId
     *
     * @return productId
     **/
    @ApiModelProperty(value = "")

    @Valid public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public ItemResponse quantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    /**
     * Get quantity
     *
     * @return quantity
     **/
    @ApiModelProperty(value = "")

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    @Override public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ItemResponse itemResponse = (ItemResponse) o;
        return Objects.equals(this.productId, itemResponse.productId) &&
               Objects.equals(this.quantity, itemResponse.quantity);
    }

    @Override public int hashCode() {
        return Objects.hash(productId, quantity);
    }

    @Override public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ItemResponse {\n");

        sb.append("    productId: ")
          .append(toIndentedString(productId))
          .append("\n");
        sb.append("    quantity: ")
          .append(toIndentedString(quantity))
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
