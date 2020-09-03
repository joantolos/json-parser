package com.joantolos.json.parser.model;

public class AllPrivate {
    private final AllPrivate self = this;

    @Override
    public String toString() {
        return self.getClass().getName();
    }
}
