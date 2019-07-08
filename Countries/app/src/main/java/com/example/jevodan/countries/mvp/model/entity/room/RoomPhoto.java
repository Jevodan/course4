package com.example.jevodan.countries.mvp.model.entity.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomPhoto {

    @NonNull
    @PrimaryKey
    public String name;
    public String urlPhoto;

    public RoomPhoto() {

    }

    public RoomPhoto(String name, String urlPhoto) {
        this.name = name;
        this.urlPhoto = urlPhoto;
    }

    public String getName() {
        return name;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}
