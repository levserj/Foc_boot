package com.levserj.repository;

import com.levserj.domain.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Serhii Levchynskyi on 25.04.2016.
 */
@Repository
public interface ItemRepository extends CrudRepository<Item, Long> {

}
