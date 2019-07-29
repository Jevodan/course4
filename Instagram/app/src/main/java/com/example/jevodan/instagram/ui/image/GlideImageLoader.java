package com.example.jevodan.instagram.ui.image;

import android.graphics.Bitmap;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.jevodan.instagram.ui.net.NetworkStatus;
import timber.log.Timber;

public class GlideImageLoader implements IImageLoader<ImageView> {


    NetworkStatus networkStatus = new NetworkStatus();

    @Override
    public void loadInto(String url, ImageView container) {
        if (networkStatus.isOnline()) {
            GlideApp
                    .with(container.getContext())
                    .asBitmap()
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .listener(new RequestListener<Bitmap>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                            Timber.e(e, "Image load failed");
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                           // ImageCache.saveImage(url, resource);
                            return false;
                        }
                    }).into(container);

        } else {
            GlideApp.with(container.getContext())
                    .asBitmap()
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(container);
        }
    }
}
