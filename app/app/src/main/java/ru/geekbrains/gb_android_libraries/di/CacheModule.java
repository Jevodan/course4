package ru.geekbrains.gb_android_libraries.di;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.gb_android_libraries.mvp.model.cache.ICache;
import ru.geekbrains.gb_android_libraries.mvp.model.cache.PaperCache;
import ru.geekbrains.gb_android_libraries.mvp.model.cache.RealmCache;
import ru.geekbrains.gb_android_libraries.mvp.model.cache.RoomCache;

import javax.inject.Named;

@Module
public class CacheModule {

    @Named("room")
    @Provides
    public ICache roomCache() {
        return new RoomCache();
    }

    @Named("realm")
    @Provides
    public ICache realmCache() {
        return new RealmCache();
    }

    @Named("paper")
    @Provides
    public ICache paperCache() {
        return new PaperCache();
    }
}
