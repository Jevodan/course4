package com.example.jevodan.countries.mvp.model.entity.room.db;


import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.jevodan.countries.mvp.model.entity.room.RoomPhoto;
import com.example.jevodan.countries.mvp.model.entity.room.dao.PhotoDao;

@androidx.room.Database(entities = {RoomPhoto.class}, version = 3)
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
            instance = Room.databaseBuilder(context, Database.class, DB_NAME).build();
        }
    }

    public abstract PhotoDao getPhotoDao();
   // public abstract RepositoryDao getRepositoryDao();
}
