package com.levserj.service.mysql;


import com.levserj.domain.User;
import com.levserj.repository.UserRepository;
import com.levserj.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by Serhii Levchynskyi on 27.04.2016.
 */
@Service
public class MySQLUserService implements UserService {

    private static final Logger LOG = LoggerFactory.getLogger(MySQLUserService.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public User readUserById(Long id) {
        return repository.findOne(id);
    }

    public User readUserByEmail(String email) {
        return repository.findUserByEmail(email);
    }

    @Override
    public List<User> readAllUsers() {
        return (List<User>) repository.findAll();
    }

    @Override
    public User updateUser(User user) {
        if (!repository.exists(user.getId())) {
            LOG.error("User with id: {} doesn't exist", user.getId());
            throw new NoSuchElementException("No such user: " + user.getId());
        }
        return repository.save(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        if (repository.exists(id)) {
            repository.delete(id);
            return true;
        }
        LOG.error("User with id:" + id + "doesn't exist");
        return false;
    }
}
