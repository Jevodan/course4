package ru.geekbrains.gb_android_libraries.mvp.model.entity.room;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

@Entity
public class RoomImage {

    public RoomImage() {
    }

    @NotNull
    @PrimaryKey
    private String url;
    private String path;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public String getPath() {
        return path;
    }
}