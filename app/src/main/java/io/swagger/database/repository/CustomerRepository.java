package io.swagger.database.repository;

import io.swagger.database.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {

    CustomerEntity getCustomerEntityByEmail(String email);

}
