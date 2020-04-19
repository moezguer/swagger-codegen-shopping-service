package io.swagger.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
