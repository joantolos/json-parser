package com.joantolos.json.parser.model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String phone;
    private List<User> followers;
    private final List<Follower> bidirectionalFollowers;

    public User() {
        this.bidirectionalFollowers = new ArrayList<>();
    }

    public User(String name) {
        this.name = name;
        this.bidirectionalFollowers = new ArrayList<>();
    }

    public User(String name, String phone, List<User> followers) {
        this.name = name;
        this.phone = phone;
        this.followers = followers;
        this.bidirectionalFollowers = new ArrayList<>();
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

    public List<Follower> getBidirectionalFollowers() {
        return bidirectionalFollowers;
    }

    public void addFollower(Follower follower) {
        this.bidirectionalFollowers.add(follower);
    }
}
