package com.example.jevodan.instagram.mvp.model.entity.room.tables;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomPhoto {

    @NonNull
    @PrimaryKey
    public String name;
    public String user;
    public String urlPhoto;
    public boolean chosen;

    public RoomPhoto() {

    }

    public RoomPhoto(String name, String urlPhoto, String user) {
        this.name = name;
        this.urlPhoto = urlPhoto;
        this.user = user;

    }

    public String getName() {
        return name;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public String getUser() {
        return user;
    }

    public void setUser(@NonNull String user) {
        this.user = user;
    }

    public boolean isChosen() {
        return chosen;
    }

    public void setChosen(boolean chosen) {
        this.chosen = chosen;
    }
}
