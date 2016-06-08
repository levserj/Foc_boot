package com.levserj.domain;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by Serhii Levchynskyi on 26.04.2016.
 */
@Entity(name = "items")
public class Item implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    @NotEmpty
    private String title;
    @NotNull
    @NotEmpty
    private String description;
    @JoinColumn
    @ManyToOne()
    private User owner;

    public Item() {
    }

    public Item(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Item(String title, String description, User owner) {

        this.title = title;
        this.description = description;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public int hashCode() {
        int hashcode = 0;
        if (title != null) {
            hashcode += title.hashCode();
        }
        if (description != null) {
            hashcode += description.hashCode();
        }
        return hashcode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Item)) {
            return false;
        }
        Item item = (Item) obj;
        if (title == null ? item.getTitle() != null :
                !title.equals(item.getTitle())) {
            return false;
        }
        return !(description == null ? item.getDescription() != null :
                !description.equals(item.getDescription()));
    }
}
