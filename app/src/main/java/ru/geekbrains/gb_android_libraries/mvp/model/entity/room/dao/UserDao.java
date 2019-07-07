package ru.geekbrains.gb_android_libraries.mvp.model.entity.room.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import ru.geekbrains.gb_android_libraries.mvp.model.entity.room.RoomUser;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

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

    @Query("SELECT * FROM roomuser")
    List<RoomUser> getAll();


    @Query("SELECT * FROM roomuser WHERE login = :login LIMIT 1")
    RoomUser findByLogin(String login);
}
