package ru.geekbrains.gb_android_libraries.mvp.model.cache;

import android.graphics.Bitmap;

import java.io.File;

public interface IImageCache {

    File getFile(String url);

    boolean contains(String url);

    void clear();

    File saveImage(final String url, Bitmap bitmap);

    File getImageDir();

    String SHA1(String s);

    float getSizeKb();

    void deleteFileOrDirRecursive(File fileOrDirectory);

    long getFileOrDirSize(File f);
}
