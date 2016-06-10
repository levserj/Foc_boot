package com.levserj.service.mysql;

import com.levserj.domain.Item;
import com.levserj.repository.ItemRepository;
import com.levserj.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Serhii Levchynskyi on 27.04.2016.
 */
@Service
public class MySQLItemService implements ItemService {

    private static final Logger LOG = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemRepository repository;

    @Override
    public Item createItem(Item item) {
        return repository.save(item);
    }

    @Override
    public Item readItemById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<Item> readAllItems() {
        return (List<Item>) repository.findAll();
    }

    @Override
    public Item updateItem(Item item) {
        if (!repository.exists(item.getId())) {
            LOG.error("Item with id: {} doesn't exist", item.getId());
            return null;
        }
        return repository.save(item);
    }

    @Override
    public boolean deleteItem(Long id) {
        if (repository.exists(id)) {
            repository.delete(id);
            return true;
        }
        LOG.error("Item with id:" + id + "doesn't exist");
        return false;
    }

    @Override
    public List<Item> readItemsByOwner(Long ownerId) {
        return repository.findItemByOwnerId(ownerId);
    }
}
