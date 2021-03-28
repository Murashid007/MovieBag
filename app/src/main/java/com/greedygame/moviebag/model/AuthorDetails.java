package com.greedygame.moviebag.model;

import java.io.Serializable;

public class AuthorDetails implements Serializable {
    private String name;
    private String avatar_path;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar_path() {
        return avatar_path;
    }

    public void setAvatar_path(String avatar_path) {
        this.avatar_path = avatar_path;
    }
}
