package com.levserj.controller;


import com.levserj.domain.User;
import com.levserj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
@RequestMapping("/rest/users")
public class UserRestController {

    private static final Logger LOG = LoggerFactory.getLogger(UserRestController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity createUser(@RequestBody User newUser, BindingResult result, UriComponentsBuilder uriComponentsBuilder) {
        if (result.hasErrors()) {
            LOG.error("Creating user: {}, BAD_REQUEST", result.getFieldError().getDefaultMessage());
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), BAD_REQUEST);
        }
        if ((newUser = userService.createUser(newUser)) != null) {
            LOG.info("User:" + newUser.getId() + " " + newUser.getLastName() + " CREATED");
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponentsBuilder.path("/rest/users/{id}").
                    buildAndExpand(newUser.getId()).toUri());
            return new ResponseEntity<>(newUser, headers, CREATED);
        } else {
            LOG.error("Creating user: CONFLICT");
            return new ResponseEntity(CONFLICT);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity readUser(@PathVariable("id") Long id) {
        User user = userService.readUserById(id);
        if (user != null) {
            LOG.info("Reading user: {}, OK", id);
            return new ResponseEntity<>(user, OK);
        } else {
            LOG.error("Reading user: {}, NO_CONTENT", id);
            return new ResponseEntity<>(NO_CONTENT);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity readAllUsers() {
        List<User> users = userService.readAllUsers();
        if (users == null) {
            return new ResponseEntity<>(NO_CONTENT);
        } else {
            LOG.info("Reading all users: OK");
            return new ResponseEntity<>(users, OK);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody User user,
                                     BindingResult result,
                                     @PathVariable("id") Long id) {
        if (result.hasErrors()) {
            LOG.error("Updating user: {}, BAD_REQUEST", result.getFieldError().getDefaultMessage());
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), BAD_REQUEST);
        }
        user.setId(id);
        if (userService.readUserById(id) != null && userService.updateUser(user) != null) {
            LOG.info("Updating user: {} OK", id);
            return new ResponseEntity(OK);
        } else {
            LOG.error("Updating user: {} NO_CONTENT", id);
            return new ResponseEntity(NO_CONTENT);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteUser(@PathVariable("id") Long id) {
        if (userService.deleteUser(id)) {
            LOG.info("Deleting user: {} OK", id);
            return new ResponseEntity(OK);
        } else {
            LOG.error("Deleting user: {} NO_CONTENT", id);
            return new ResponseEntity(NO_CONTENT);
        }
    }
}
