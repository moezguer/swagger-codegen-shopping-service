package io.swagger.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(length = 16)
    private UUID itemId;

    private Integer quantity;

    @OneToOne(cascade = CascadeType.ALL)
    private ProductEntity product;

    @ManyToOne(cascade = CascadeType.ALL)
    private OrderEntity order;

    @ManyToOne(cascade = CascadeType.ALL)
    private CartEntity cart;

}
