package io.swagger.database.repository;

import io.swagger.database.model.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, UUID> {

    CartEntity getCartEntityByCustomer_Email(String email);


}
