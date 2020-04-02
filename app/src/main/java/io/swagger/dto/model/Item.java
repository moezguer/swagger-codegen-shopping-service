package io.swagger.dto.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.database.model.CartEntity;
import io.swagger.database.model.OrderEntity;
import io.swagger.database.model.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    private UUID productId;

    private Integer quantity;

    private Product product;

    private Order order;

    private Cart cart;

}
