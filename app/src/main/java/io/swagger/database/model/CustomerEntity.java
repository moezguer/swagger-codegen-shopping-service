package io.swagger.database.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerEntity {

    @Id
    private String email;

    private String name;

    private String surname;

    @OneToOne(cascade = CascadeType.ALL)
    private CartEntity cart;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<OrderEntity> orders;


}
