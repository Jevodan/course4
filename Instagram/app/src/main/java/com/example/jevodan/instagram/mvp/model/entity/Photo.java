package com.example.jevodan.instagram.mvp.model.entity;

import com.google.gson.annotations.Expose;

public class Photo {

    @Expose
    private String name;
    @Expose
    private String urlPhoto;

    public Photo(String name, String urlPhoto) {
        this.name = name;
        this.urlPhoto = urlPhoto;

    }

    public String getName() {
        return name;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }
}
