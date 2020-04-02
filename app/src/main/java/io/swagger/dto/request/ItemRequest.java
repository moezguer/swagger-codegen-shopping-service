package io.swagger.dto.request;

import io.swagger.dto.model.Cart;
import io.swagger.dto.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRequest {

    private UUID productId;

    private Integer quantity;

}
