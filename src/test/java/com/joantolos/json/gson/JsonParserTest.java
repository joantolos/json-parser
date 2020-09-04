package com.joantolos.json.gson;

import com.joantolos.json.model.AllPrivate;
import com.joantolos.json.model.Follower;
import com.joantolos.json.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

        this.joanJson = "{\"name\":\"Joan\",\"phone\":\"1234\",\"followers\":[{\"name\":\"John Doe\",\"phone\":\"5678\",\"bidirectionalFollowers\":[]},{\"name\":\"Jane Doe\",\"phone\":\"9101112\",\"bidirectionalFollowers\":[]}],\"bidirectionalFollowers\":[]}";
    }

    @Test
    public void shouldStringifyObject() {
        Assert.assertEquals(joanJson, jsonParser.stringify(joan));
    }

    @Test
    public void shouldParseJson() {
        User joan = (User) jsonParser.parse(joanJson, User.class);
        Assert.assertEquals("Joan", joan.getName());
        Assert.assertEquals("1234", joan.getPhone());
        Assert.assertEquals(2, joan.getFollowers().size());
    }

    @Test(expected = StackOverflowError.class)
    public void shouldRaiseExceptionTryingToSerializeClassWithCircularStructure() {
        User user = new User("Joan");
        Follower follower = new Follower("1", user);
        user.addFollower(follower);

        jsonParser.stringify(follower);
    }

    @Test
    public void shouldSerializeClassWithNoPublicPropertiesOrConstructor() {
        jsonParser.stringify(new AllPrivate());
    }

}
