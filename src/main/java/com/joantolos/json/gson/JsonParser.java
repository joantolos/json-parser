package com.joantolos.json.gson;

import com.google.gson.Gson;

public class JsonParser {

    private final Gson gson;

    public JsonParser() {
        this.gson = new Gson();
    }

    public String stringify(Object o){
        return gson.toJson( o );
    }

    public Object parse(String json, Class c){
        return gson.fromJson( json, c );
    }
}
