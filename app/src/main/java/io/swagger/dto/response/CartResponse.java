package io.swagger.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * CartResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen",
                            date = "2020-03-27T16:32:59.829Z[GMT]")
public class CartResponse {
    @JsonProperty("cartItems")
    @Valid
    private List<ItemResponse> cartItems = null;

    public CartResponse cartItems(final List<ItemResponse> cartItems) {
        this.cartItems = cartItems;
        return this;
    }

    public CartResponse addCartItemsItem(final ItemResponse cartItemsItem) {
        if (this.cartItems == null) {
            this.cartItems = new ArrayList<ItemResponse>();
        }
        this.cartItems.add(cartItemsItem);
        return this;
    }

    /**
     * Get cartItems
     *
     * @return cartItems
     **/
    @ApiModelProperty(value = "")
    @Valid
    public List<ItemResponse> getCartItems() {
        return cartItems;
    }

    public void setCartItems(final List<ItemResponse> cartItems) {
        this.cartItems = cartItems;
    }


    @Override
    public boolean equals(final java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CartResponse cartResponse = (CartResponse) o;
        return Objects.equals(this.cartItems, cartResponse.cartItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartItems);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CartResponse {\n");

        sb.append("    cartItems: ").append(toIndentedString(cartItems)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(final java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
