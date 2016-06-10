package com.levserj.model;

import com.levserj.domain.Item;
import com.levserj.domain.User;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by Serhii Levchynskyi on 08.06.2016.
 */
public class ItemTest {

    private static List<Item> items;
    private static Item newItem;
    private static User newUser;

    @BeforeClass
    public static void setUp() {
        newItem = new Item("item1", "old stuff");
        newUser = new User("z@z", "firstN", "lastN", items, "z");
        newItem.setOwner(newUser);
    }

    @Test
    public void createdItemHasAllFields() {
        assertThat(newItem.getTitle(), is("item1"));
        assertThat(newItem.getDescription(), is("old stuff"));
        assertThat(newItem.getOwner(), is(equalTo(newUser)));
        assertThat(newItem.getOwner().getPassword(), is(equalTo(newUser.getPassword())));
    }

    @Test
    public void emptyItemAndNonEmptyAreNotEqual() {
        assertThat(newItem, is(not(equalTo(new Item()))));
    }

    @Test
    public void emptyItemHasDifferentHashCodeThanNotEmpty() {
        assertThat(newItem.hashCode(), is(not(equalTo(new Item().hashCode()))));
    }
}
