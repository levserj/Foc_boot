package com.levserj.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Serhii Levchynskyi on 26.04.2016.
 */
@Entity
public class Item {

    @Id
    @GeneratedValue
    private Long id;
}
