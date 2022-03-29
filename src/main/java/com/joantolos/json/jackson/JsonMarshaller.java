package com.joantolos.json.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;

public class JsonMarshaller<T> {
    private final ObjectMapper mapper;
    private final Class<T> targetClass;

    public JsonMarshaller(Class<T> targetClass) {
        mapper = new ObjectMapper();
        this.targetClass = targetClass;
    }

    public String marshall(T object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    public T unmarshall(String json) throws JsonProcessingException {
        return mapper.readValue(json, targetClass);
    }

    public HashMap<String, T> unmarshallToMap(String json) throws JsonProcessingException {
        TypeReference<HashMap<String, T>> typeRef = new TypeReference<>() {};
        return mapper.readValue(json, typeRef);
    }

    public String marshallList(List<T> list) throws JsonProcessingException {
        return mapper.writeValueAsString(list);
    }

    public List<T> unmarshallList(String json) throws JsonProcessingException {
        return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, targetClass));
    }

}
