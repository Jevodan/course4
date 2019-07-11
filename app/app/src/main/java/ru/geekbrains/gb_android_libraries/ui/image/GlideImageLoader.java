package ru.geekbrains.gb_android_libraries.ui.image;

import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.amitshekhar.DebugDB;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import ru.geekbrains.gb_android_libraries.mvp.model.api.INetworkStatus;
import ru.geekbrains.gb_android_libraries.mvp.model.cache.IImageCache;
import ru.geekbrains.gb_android_libraries.mvp.model.cache.ImageCacheRealm;
import ru.geekbrains.gb_android_libraries.mvp.model.image.IImageLoader;

public class GlideImageLoader implements IImageLoader<ImageView> {

    private INetworkStatus networkStatus;
    private IImageCache iImageCache;

    public GlideImageLoader(INetworkStatus networkStatus, IImageCache iImageCache) {
        this.networkStatus = networkStatus;
        this.iImageCache = iImageCache;
    }

    @Override
    public void loadInto(String url, ImageView container) {
        if (networkStatus.isOnline()) {
            Glide.with(container.getContext())
                    .asBitmap()
                    .load(url)
                    .listener(new RequestListener<Bitmap>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                            iImageCache.saveImage(url, resource);
                            return false;
                        }
                    })
                    .into(container);
            DebugDB.getAddressLog();
        } else {
            if (iImageCache.contains(url)) {
                GlideApp.with(container.getContext())
                        .load(iImageCache.getFile(url))
                        .into(container);
                Log.d("1555", url);
                DebugDB.getAddressLog();
            }
        }


    }
}
