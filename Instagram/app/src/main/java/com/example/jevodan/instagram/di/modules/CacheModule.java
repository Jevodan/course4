package com.example.jevodan.instagram.di.modules;


import com.example.jevodan.instagram.mvp.model.cache.IDataCache;
import com.example.jevodan.instagram.mvp.model.cache.PaperCache;
import com.example.jevodan.instagram.mvp.model.cache.RealmCache;
import com.example.jevodan.instagram.mvp.model.cache.RoomCache;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class CacheModule {

    @Singleton
    @Named("room")
    @Provides
    public IDataCache roomCache() {
        return new RoomCache();
    }

    @Singleton
    @Named("realm")
    @Provides
    public IDataCache realmCache() {
        return new RealmCache();
    }

    @Singleton
    @Named("paper")
    @Provides
    public IDataCache paperCache() {
        return new PaperCache();
    }

}
