package com.example.jevodan.instagram.mvp.model.entity.room.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.jevodan.instagram.mvp.model.entity.room.tables.RoomUser;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    @Insert(onConflict = REPLACE)
    void insert(RoomUser user);

    @Insert(onConflict = REPLACE)
    void insert(RoomUser... user);

    @Insert(onConflict = REPLACE)
    void insert(List<RoomUser> user);

    @Insert
    void update(RoomUser user);

    @Insert
    void update(RoomUser... user);

    @Insert
    void update(List<RoomUser> user);

    @Delete
    void delete(RoomUser user);

    @Delete
    void delete(RoomUser... user);

    @Delete
    void delete(List<RoomUser> user);

    @Query("SELECT * FROM RoomUser")
    List<RoomUser> getAll();


    @Query("SELECT * FROM RoomUser WHERE id = :id LIMIT 1")
    RoomUser findById(String id);
    
}
