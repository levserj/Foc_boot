package com.levserj.service;

import com.levserj.domain.Item;

import java.util.List;

/**
 * Created by Serhii Levchynskyi on 27.04.2016.
 */
public interface ItemService {

    Item createItem(Item item);

    Item readItemById(Long id);

    List<Item> readAllItems();

    Item updateItem(Item item);

    boolean deleteItem(Long id);

    List<Item> readItemsByOwner(Long ownerId);

}
