package io.swagger.service;

import io.swagger.database.model.ItemEntity;
import io.swagger.database.repository.ItemRepository;
import io.swagger.dto.model.Item;
import io.swagger.exception.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ItemService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ItemRepository itemRepository;

    public Item addItemToCart(Item item) {

        final ItemEntity itemEntity = modelMapper.map(item, ItemEntity.class);
        final ItemEntity savedItemEntity = itemRepository.save(itemEntity);
        final Item savedItem = modelMapper.map(savedItemEntity, Item.class);
        return savedItem;
    }

    public void deleteListOfItems(List<Item> itemList) {
        final List<ItemEntity> itemEntityList = itemList.stream()
                                                        .map(items -> modelMapper.map(items, ItemEntity.class))
                                                        .collect(Collectors.toList());
        itemRepository.deleteAll(itemEntityList);
    }

    public List<Item> getItemEntitiesByCart_Customer_Email(String email) {

        final List<ItemEntity> itemEntityList = itemRepository.findItemEntitiesByCart_Customer_Email(email);

        if (itemEntityList.isEmpty()) {
            throw new NotFoundException(email);
        } else {
            final List<Item> returnedItemList = itemEntityList.stream()
                                                              .map(itemEntity -> modelMapper.map(itemEntity,
                                                                      Item.class))
                                                              .collect(Collectors.toList());
            return returnedItemList;
        }
    }

    public void deleteItem(String email, UUID productId) {

        final List<ItemEntity> itemEntityList =
                itemRepository.findItemEntityByCart_Customer_EmailAndProduct_Id(email, productId);
        if (itemEntityList.isEmpty()) {
            throw new NotFoundException(productId.toString());
        } else {
            itemRepository.deleteAll(itemEntityList);
        }
    }

    public void saveItemsToOrder(List<Item> itemsToBeSaved) {

        final List<ItemEntity> itemEntityList = itemsToBeSaved.stream()
                                                              .map(itemList -> modelMapper.map(itemList,
                                                                      ItemEntity.class))
                                                              .collect(Collectors.toList());
        itemRepository.saveAll(itemEntityList);

    }


}
