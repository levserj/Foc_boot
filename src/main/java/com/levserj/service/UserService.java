package com.levserj.service;

import com.levserj.domain.User;

import java.util.List;

/**
 * Created by Serhii Levchynskyi on 27.04.2016.
 */
public interface UserService {

    User createUser(User user);

    User readUserById(Long id);

    List<User> readAllUsers();

    User updateUser(User user);

    boolean deleteUser(Long id);

}
