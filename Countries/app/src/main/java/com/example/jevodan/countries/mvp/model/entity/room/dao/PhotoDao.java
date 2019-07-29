package com.example.jevodan.countries.mvp.model.entity.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.jevodan.countries.mvp.model.entity.room.RoomPhoto;

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

    @Insert
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

    @Query("SELECT * FROM roomphoto")
    List<RoomPhoto> getAll();


    @Query("SELECT * FROM roomphoto WHERE name = :name LIMIT 1")
    RoomPhoto findByLogin(String name);

}
