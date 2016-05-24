package com.levserj.repository;

import com.levserj.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Serhii Levchynskyi on 11.05.2016.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findUserByEmail(String email);
}
