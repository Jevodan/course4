package ru.geekbrains.gb_android_libraries.mvp.model.entity.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import ru.geekbrains.gb_android_libraries.mvp.model.entity.room.RoomImage;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ImageDao {

    @Query("SELECT * FROM roomimage WHERE url = :url LIMIT 1")
    RoomImage findOne(String url);

    @Insert(onConflict = REPLACE)
    void insert(RoomImage image);

}
