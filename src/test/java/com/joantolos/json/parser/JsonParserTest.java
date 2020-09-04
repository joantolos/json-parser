package com.joantolos.json.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.joantolos.json.parser.model.AllPrivate;
import com.joantolos.json.parser.model.Follower;
import com.joantolos.json.parser.model.User;
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

        this.joanJson = "{\"name\":\"Joan\",\"phone\":\"1234\",\"followers\":[{\"name\":\"John Doe\"," +
                "\"phone\":\"5678\",\"followers\":null,\"bidirectionalFollowers\":[]},{\"name\":\"Jane Doe\"," +
                "\"phone\":\"9101112\",\"followers\":null,\"bidirectionalFollowers\":[]}],\"bidirectionalFollowers\":[]}";
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

    @Test(expected = JsonMappingException.class)
    public void shouldRaiseExceptionTryingToSerializeClassWithCircularStructure() throws JsonProcessingException {
        User user = new User("Joan");
        Follower follower = new Follower("1", user);
        user.addFollower(follower);

        jsonParser.stringify(follower);
    }

    @Test(expected = InvalidDefinitionException.class)
    public void shouldRaiseExceptionTryingToSerializeClassWithNoPublicPropertiesOrConstructor() throws JsonProcessingException {
        jsonParser.stringify(new AllPrivate());
    }

    @Test
    public void shouldSerializeClassWithNoPublicPropertiesOrConstructorWhenIgnoringVisibility() throws JsonProcessingException {
        jsonParser.ignoreVisibility();
        Assert.assertNotNull(jsonParser.stringify(new AllPrivate()));
    }

}
