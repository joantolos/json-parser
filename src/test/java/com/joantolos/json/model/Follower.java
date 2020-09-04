package com.joantolos.json.model;

public class Follower {
    private String id;
    private User user;

    public Follower() {
    }

    public Follower(String id, User user) {
        this.id = id;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }
}
