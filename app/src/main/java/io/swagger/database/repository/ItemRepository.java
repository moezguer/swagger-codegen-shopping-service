package io.swagger.database.repository;

import io.swagger.database.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository <ItemEntity, UUID> {

    List<ItemEntity> getItemEntitiesByCart_Customer_Email(String email);

    ItemEntity getItemEntityByCart_Customer_EmailAndProduct_Id(String email, UUID product);
}
