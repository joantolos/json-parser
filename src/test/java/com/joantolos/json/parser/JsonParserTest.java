package com.joantolos.json.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonParserTest {

    private JsonParser jsonParser;
    private User joan;
    private String joanJson;

    @Before
    public void setUp() {
        this.jsonParser = new JsonParser();
        List<User> joanFollowers = new ArrayList<>();
        joanFollowers.add(new User("John Doe", "5678", null));
        joanFollowers.add(new User("Jane Doe", "9101112", null));
        this.joan = new User("Joan", "1234", joanFollowers);

        this.joanJson = "{\"name\":\"Joan\",\"phone\":\"1234\",\"followers\":[{\"name\":\"John Doe\",\"phone\":\"5678\",\"followers\":null},{\"name\":\"Jane Doe\",\"phone\":\"9101112\",\"followers\":null}]}";
    }

    @Test
    public void shouldStringifyObject() throws JsonProcessingException {
        Assert.assertEquals(joanJson, jsonParser.stringify(joan));
    }

    @Test
    public void shouldParseJson() throws JsonProcessingException {
        User joan = (User) jsonParser.parse(joanJson, User.class);
        Assert.assertEquals("Joan", joan.getName());
        Assert.assertEquals("1234", joan.getPhone());
        Assert.assertEquals(2, joan.getFollowers().size());
    }

    static class User {
        private String name;
        private String phone;
        private List<User> followers;

        public User() {
        }

        public User(String name, String phone, List<User> followers) {
            this.name = name;
            this.phone = phone;
            this.followers = followers;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public List<User> getFollowers() {
            return followers;
        }
    }
}
