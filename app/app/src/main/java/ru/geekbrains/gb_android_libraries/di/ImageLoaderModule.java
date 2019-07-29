package ru.geekbrains.gb_android_libraries.di;

import android.widget.ImageView;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

import ru.geekbrains.gb_android_libraries.mvp.model.api.INetworkStatus;
import ru.geekbrains.gb_android_libraries.mvp.model.cache.IImageCache;
import ru.geekbrains.gb_android_libraries.mvp.model.image.IImageLoader;

import ru.geekbrains.gb_android_libraries.ui.image.GlideImageLoader;

@Module(includes = {UtilsModule.class, CacheModuleImage.class})
public class ImageLoaderModule {

    @Provides
    public IImageLoader<ImageView> imageLoader(INetworkStatus networkStatus, @Named("paperimage") IImageCache iImageCache) {
        return new GlideImageLoader(networkStatus, iImageCache);
    }



}
