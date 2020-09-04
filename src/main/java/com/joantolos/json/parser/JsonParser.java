package com.joantolos.json.parser;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {

    private final ObjectMapper mapper;

    public JsonParser() {
        this.mapper = new ObjectMapper();
    }

    public String stringify(Object o) throws JsonProcessingException {
        return this.mapper.writeValueAsString(o);
    }

    public Object parse(String json, Class c) throws JsonProcessingException {
        return this.mapper.readValue(json, c);
    }

    public void ignoreVisibility() {
        this.mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    }
}
