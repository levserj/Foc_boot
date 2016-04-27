package com.levserj.repository;

import com.levserj.domain.Item;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Serhii Levchynskyi on 25.04.2016.
 */
public interface ItemRepository extends CrudRepository<Item, Long> {

}
