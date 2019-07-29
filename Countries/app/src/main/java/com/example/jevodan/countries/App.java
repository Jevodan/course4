package com.example.jevodan.countries;

import android.app.Application;


import com.example.jevodan.countries.mvp.model.entity.room.db.Database;

import io.paperdb.Paper;
import timber.log.Timber;

public class App extends Application {
    static private App instance;


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Paper.init(this);
        Database.create(this);
        Timber.plant(new Timber.DebugTree());

        /*
        Realm.init(this);

        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        */
    }

    public static App getInstance() {
        return instance;
    }
}
