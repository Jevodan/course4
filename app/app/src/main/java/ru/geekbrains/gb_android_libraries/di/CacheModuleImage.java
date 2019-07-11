package ru.geekbrains.gb_android_libraries.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.gb_android_libraries.mvp.model.cache.ICache;
import ru.geekbrains.gb_android_libraries.mvp.model.cache.IImageCache;
import ru.geekbrains.gb_android_libraries.mvp.model.cache.ImageCachePapper;
import ru.geekbrains.gb_android_libraries.mvp.model.cache.ImageCacheRealm;
import ru.geekbrains.gb_android_libraries.mvp.model.cache.ImageCacheRoom;
import ru.geekbrains.gb_android_libraries.mvp.model.cache.PaperCache;
import ru.geekbrains.gb_android_libraries.mvp.model.cache.RealmCache;
import ru.geekbrains.gb_android_libraries.mvp.model.cache.RoomCache;

@Module
public class CacheModuleImage {

    @Named("roomimage")
    @Provides
    public IImageCache roomCacheImage() {
        return new ImageCacheRoom();
    }

    @Named("realmimage")
    @Provides
    public IImageCache realmCacheImage() {
        return new ImageCacheRealm();
    }

    @Named("paperimage")
    @Provides
    public IImageCache paperCacheImage() {
        return new ImageCachePapper();
    }
}
