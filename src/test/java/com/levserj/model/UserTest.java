package com.levserj.model;

import com.levserj.domain.Item;
import com.levserj.domain.User;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

/**
 * Created by Serhii Levchynskyi on 06.06.2016.
 */
public class UserTest {

    private static List<Item> items;
    private static Item newItem;
    private static User newUser;
    private static User emptyUser;

    @BeforeClass
    public static void setUp() {
        items = new ArrayList<>();
        newItem = new Item("item1", "old stuff");
        items.add(newItem);
        newUser = new User("z@z", "firstN", "lastN", items, "z");
        newItem.setOwner(newUser);
        emptyUser = new User();
    }

    @Test
    public void initializedUser() {
        assertThat(emptyUser.getId(), is(equalTo(null)));
        assertThat(emptyUser.getEmail(), is(equalTo(null)));
        assertThat(emptyUser.getFirstName(), is(equalTo(null)));
        assertThat(emptyUser.getLastName(), is(equalTo(null)));
        assertThat(emptyUser.getPassword(), is(equalTo(null)));
        assertThat(emptyUser.getItems(), is(equalTo(new ArrayList<>())));
    }

    @Test
    public void createdUserHasAllTheParametersFromConstrucor() {
        assertThat(newUser.getEmail(), is("z@z"));
        assertThat(newUser.getFirstName(), is("firstN"));
        assertThat(newUser.getLastName(), is("lastN"));
        assertThat(newUser.getPassword(), is("z"));
        assertThat(newUser.getItems(), is(equalTo(items)));

    }

    @Test
    public void hashcodeOfEmptyAndNotEmptyUserAreNotEqual() {
        assertThat(newUser.hashCode(), is(not(equalTo(emptyUser.hashCode()))));
    }

    @Test
    public void userHasItemWePutInHisItemsList() {
        assertThat(newUser.getItems(), contains(equalTo(newItem)));
    }

    @Test
    public void emptyUserIsNotEqualToUserFilledWithData() {
        assertThat(newUser, is(not(equalTo(emptyUser))));
    }

}
