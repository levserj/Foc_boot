package com.levserj.domain;

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
    private String title;
    @NotNull
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
        return title.hashCode() + description.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return ((obj instanceof Item) && (((Item) obj).getId().equals(this.getId())));
    }
}
