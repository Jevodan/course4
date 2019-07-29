package com.example.jevodan.instagram.mvp.model.entity.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.jevodan.instagram.mvp.model.entity.room.tables.RoomPhoto;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface PhotoDao {

    @Insert(onConflict = REPLACE)
    void insert(RoomPhoto photo);

    @Insert(onConflict = REPLACE)
    void insert(RoomPhoto... photo);

    @Insert(onConflict = REPLACE)
    void insert(List<RoomPhoto> photo);

    @Update
    void update(RoomPhoto photo);

    @Insert
    void update(RoomPhoto... photo);

    @Insert
    void update(List<RoomPhoto> photo);

    @Delete
    void delete(RoomPhoto photo);

    @Delete
    void delete(RoomPhoto... photo);

    @Delete
    void delete(List<RoomPhoto> photo);

    @Query("SELECT * FROM roomphoto WHERE user = :user")
    List<RoomPhoto> getAll(String user);


    @Query("SELECT * FROM roomphoto WHERE urlPhoto = :url LIMIT 1")
    RoomPhoto findByUrl(String url);

    @Query("SELECT * FROM roomphoto WHERE name = :name LIMIT 1")
    RoomPhoto findByName(String name);

    @Query("SELECT * FROM roomphoto WHERE user = :user AND chosen = 1")
    List<RoomPhoto> findChosen(String user);
}
