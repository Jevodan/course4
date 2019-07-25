package com.example.jevodan.instagram.mvp.model.entity.room.db;


import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.jevodan.instagram.mvp.model.entity.room.dao.UserDao;
import com.example.jevodan.instagram.mvp.model.entity.room.tables.RoomPhoto;
import com.example.jevodan.instagram.mvp.model.entity.room.dao.PhotoDao;
import com.example.jevodan.instagram.mvp.model.entity.room.tables.RoomUser;


@androidx.room.Database(entities = {RoomPhoto.class, RoomUser.class}, version = 1)
public abstract class Database extends RoomDatabase {

    private static final String DB_NAME = "database.db";
    private static volatile Database instance;

    public static synchronized Database getInstance(){
        if(instance == null){
            throw new RuntimeException("Database has not been created. Please call create()");
        }
        return instance;
    }

    public static void create(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context, Database.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
    }

    public abstract PhotoDao getPhotoDao();
    public abstract UserDao getUserDao();
}
