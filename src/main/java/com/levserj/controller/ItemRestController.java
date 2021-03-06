package com.levserj.controller;

import com.levserj.domain.Item;
import com.levserj.domain.User;
import com.levserj.service.ItemService;
import com.levserj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

/**
 * Created by Serhii Levchynskyi on 28.04.2016.
 */
@RestController
@RequestMapping("/rest/items")
public class ItemRestController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createItem(@RequestBody Item newItem,
                                     BindingResult result,
                                     UriComponentsBuilder uriComponentsBuilder) {
        if (result.hasErrors()) {
            LOG.error("Creating item: {}, BAD_REQUEST", result.getFieldError().getDefaultMessage());
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), BAD_REQUEST);
        }
        if ((newItem = itemService.createItem(newItem)) != null) {
            LOG.info("Item:" + newItem.getId() + " " + newItem.getTitle() + " CREATED");
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponentsBuilder.path("/rest/items/{id}").
                    buildAndExpand(newItem.getId()).toUri());
            return new ResponseEntity<>(newItem, headers, HttpStatus.CREATED);
        } else {
            LOG.error("Creating item: CONFLICT");
            return new ResponseEntity(CONFLICT);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity readItem(@PathVariable("id") Long id) {
        Item item = itemService.readItemById(id);
        if (item != null) {
            LOG.info("Reading item: {}, OK", id);
            return new ResponseEntity<>(item, OK);
        } else {
            LOG.error("Reading item: {}, NO_CONTENT", id);
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity readAllItems() {
        List<Item> items = itemService.readAllItems();
        if (items == null) {
            return new ResponseEntity<>(NO_CONTENT);
        } else {
            LOG.info("Reading all items: OK");
            return new ResponseEntity<>(items, OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateItem(@RequestBody Item item,
                                     BindingResult result,
                                     @PathVariable("id") Long id) {
        if (result.hasErrors()) {
            LOG.error("Updating item: {}, BAD_REQUEST", result.getFieldError().getDefaultMessage());
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), BAD_REQUEST);
        }
        Item oldItem = itemService.readItemById(id);
        if (oldItem != null) {
            item.setOwner(oldItem.getOwner());
            item.setId(oldItem.getId());
            itemService.updateItem(item);
            LOG.info("Updating item: {} OK", id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            LOG.error("Updating item: {} NO_CONTENT", id);
            return new ResponseEntity(NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteItem(@PathVariable("id") Long id) {
        if (itemService.deleteItem(id)) {
            LOG.info("Deleting item: {} OK", id);
            return new ResponseEntity(OK);
        } else {
            LOG.error("Deleting item: {} NO_CONTENT", id);
            return new ResponseEntity(NO_CONTENT);
        }
    }

    @RequestMapping(value = "/user/{ownerId}", method = RequestMethod.GET)
    public ResponseEntity readItemsByUserId(@PathVariable("ownerId") Long id) {
        List<Item> items = itemService.readItemsByOwner(id);
        if (items == null) {
            return new ResponseEntity<>(NO_CONTENT);
        } else {
            LOG.info("Reading items by user id = {}: OK", id);
            return new ResponseEntity<>(items, OK);
        }
    }

    @RequestMapping(value = "/user/{ownerId}", method = RequestMethod.POST)
    public ResponseEntity createItemAndAddItToUser(@PathVariable("ownerId") Long id,
                                                   @RequestBody Item item,
                                                   BindingResult result) {
        User user = userService.readUserById(id);
        if (user == null) {
            LOG.error("User with id = {} not found", id);
            return new ResponseEntity<>(BAD_REQUEST);
        }
        if (result.hasErrors()) {
            LOG.error("Couldn't create item from request body");
            return new ResponseEntity<>(BAD_REQUEST);
        } else {
            item.setOwner(user);
            item = itemService.createItem(item);
            user.getItems().add(item);
            userService.updateUser(user);
            LOG.info("Item with id = {} created and added to user id = {}", item.getId(), id);
            return new ResponseEntity<>(item, OK);
        }
    }

    @RequestMapping(value = "{itemId}/user/{ownerId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteItemFromUser(@PathVariable("ownerId") Long ownerId,
                                             @PathVariable("itemId") Long itemId) {
        User user = userService.readUserById(ownerId);
        Item item = itemService.readItemById(itemId);
        if (user == null) {
            LOG.error("User with id = {} not found", ownerId);
            return new ResponseEntity<>(BAD_REQUEST);
        }
        if (item == null) {
            LOG.error("Item with id = {} not found", itemId);
            return new ResponseEntity<>(BAD_REQUEST);
        } else {
            user.getItems().remove(item);
            userService.updateUser(user);
            itemService.deleteItem(item.getId());
            LOG.info("Item with id = {} deleted from user id = {}", itemId, ownerId);
            return new ResponseEntity<>(item, HttpStatus.OK);
        }
    }
}
