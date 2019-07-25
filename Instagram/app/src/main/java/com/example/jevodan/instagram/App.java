package com.example.jevodan.instagram;

import android.app.Application;

import com.example.jevodan.instagram.di.AppComponent;
import com.example.jevodan.instagram.di.AppModule;
import com.example.jevodan.instagram.di.DaggerAppComponent;
import com.example.jevodan.instagram.mvp.model.entity.room.db.Database;
import com.facebook.stetho.Stetho;
import timber.log.Timber;

public class App extends Application {

    static private App instance;

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Timber.plant(new Timber.DebugTree());
        Database.create(this);
        //   Paper.init(this);

        //  Realm.init(this);
        //   RealmConfiguration config = new RealmConfiguration.Builder()
        //           .deleteRealmIfMigrationNeeded()
        //            .build();

        //    Realm.setDefaultConfiguration(config);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public static App getInstance() {
        return instance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void initStetho(){
        // Create an InitializerBuilder
        Stetho.InitializerBuilder initializerBuilder =
                Stetho.newInitializerBuilder(this);

        // Enable Chrome DevTools
        initializerBuilder.enableWebKitInspector(
                Stetho.defaultInspectorModulesProvider(this)
        );

        // Enable command line interface
        initializerBuilder.enableDumpapp(
                Stetho.defaultDumperPluginsProvider(this)
        );

        // Use the InitializerBuilder to generate an Initializer
        Stetho.Initializer initializer = initializerBuilder.build();

        // Initialize Stetho with the Initializer
        Stetho.initialize(initializer);
    }

}
