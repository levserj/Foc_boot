package com.levserj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serhii Levchynskyi on 26.04.2016.
 */
@Entity
public class User implements Serializable, Comparable<User> {

    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String email;
    @NotEmpty
    @NotNull
    private String firstName;
    @NotEmpty
    @NotNull
    private String lastName;
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "owner")
    private List<Item> items;
    @NotEmpty
    @NotNull
    private String password;

    private String authorities;

    public User() {
    }

    public User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String password, String lastName, String firstName, String email) {
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }


    public User(String email, String firstName, String lastName, List<Item> items, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.items = items;
        this.password = password;
    }

    public User(String authorities, String password, String lastName, String firstName, String email) {
        this.authorities = authorities;
        this.password = password;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Item> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof User)) {
            return false;
        }
        User user = (User) obj;
        return (email != null ? email.equals(user.getEmail()) : user.getEmail() == null);
    }

    @Override
    public int compareTo(User other) {
        if (this == other) {
            return 0;
        }
        if (!this.getLastName().equals(other.getLastName())) {
            return this.getLastName().compareTo(other.getLastName());
        }
        if (!this.getFirstName().equals(other.getFirstName())) {
            return this.getFirstName().compareTo(other.getFirstName());
        }
        return this.getEmail().compareTo(other.getEmail());
    }
}
