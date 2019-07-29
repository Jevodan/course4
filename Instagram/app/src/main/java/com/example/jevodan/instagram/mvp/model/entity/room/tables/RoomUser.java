package com.example.jevodan.instagram.mvp.model.entity.room.tables;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity
public class RoomUser {

    @NotNull
    @PrimaryKey
    private String id;
    private String login;
    private String username;
    private String avatarUrl;

    public RoomUser() {
    }

    public RoomUser(@NotNull String id, String login, String username, String avatarUrl) {
        this.id = id;
        this.login = login;
        this.username = username;
        this.avatarUrl = avatarUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
