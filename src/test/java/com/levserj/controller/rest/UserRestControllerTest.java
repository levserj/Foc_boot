package com.levserj.controller.rest;

import com.jayway.restassured.RestAssured;
import com.levserj.Application;
import com.levserj.domain.User;
import com.levserj.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_CREATED;


/**
 * Created by Serhii Levchynskyi on 08.06.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
@WebIntegrationTest("server.port:0")
@DirtiesContext
public class UserRestControllerTest {

    @Autowired
    private UserService userService;

    @Value("${local.server.port}")
    protected int port;

    private User user;
    private String id;

    @Before
    public void setUp() {
        user = new User("y@y", "firstN", "lastN", "y");

        RestAssured.port = port;
    }

    @After
    public void tearDown() {
        System.out.println("+++++++++++++++++++++ " + id);
        userService.deleteUser(Long.parseLong(id));
    }

    // Works, if rest url's are not secured
    @Ignore
    @Test
    public void whenItemIsCreatedStatusCodeOk() {
        id = given()
                .contentType(JSON)
                .body(user)
                .when()
                .post("/rest/users")
                .then()
                .statusCode(SC_CREATED)
                .extract()
                .jsonPath()
                .getString("id").replaceAll("\\[", "").replaceAll("\\]", "");
    }
}
