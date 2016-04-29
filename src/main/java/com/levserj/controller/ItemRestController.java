package com.levserj.controller;

import com.levserj.domain.Item;
import com.levserj.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import static org.springframework.http.HttpStatus.*;

/**
 * Created by Serhii Levchynskyi on 28.04.2016.
 */
@RestController
@RequestMapping("/rest")
public class ItemRestController {

    private static final Logger LOG = LoggerFactory.getLogger(ItemRestController.class);

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/items", method = RequestMethod.POST)
    public ResponseEntity createItem(@RequestBody Item newItem, BindingResult result, UriComponentsBuilder uriComponentsBuilder) {
        if (result.hasErrors()) {
            LOG.error("Creating item: {}, BAD_REQUEST", result.getFieldError().getDefaultMessage());
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), BAD_REQUEST);
        }
        if ((newItem = itemService.createItem(newItem)) != null) {
            LOG.info("Item:" + newItem.getId() + " " + newItem.getName() + " CREATED");
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponentsBuilder.path("/rest/items/{id}").
                    buildAndExpand(newItem.getId()).toUri());
            return new ResponseEntity<>(newItem, headers, CREATED);
        } else {
            LOG.error("Creating item: CONFLICT");
            return new ResponseEntity(CONFLICT);
        }
    }
}
