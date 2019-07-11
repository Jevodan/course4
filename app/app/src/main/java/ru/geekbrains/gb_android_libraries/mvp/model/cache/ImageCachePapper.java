package ru.geekbrains.gb_android_libraries.mvp.model.cache;

import android.graphics.Bitmap;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.paperdb.Paper;
import ru.geekbrains.gb_android_libraries.App;
import ru.geekbrains.gb_android_libraries.mvp.model.entity.PImage;
import ru.geekbrains.gb_android_libraries.mvp.model.entity.room.RoomImage;
import ru.geekbrains.gb_android_libraries.mvp.model.entity.room.db.Database;
import timber.log.Timber;


public class ImageCachePapper implements IImageCache {

    private final String IMAGE_FOLDER_NAME = "image";

    @Override
    public File getFile(String url) {

        PImage paper = Paper.book("images").read(SHA1(url));
        Log.d("5555", url);
        if (paper != null) {
            Log.d("555", url);
            return new File(paper.getPath());
        }
        return null;
    }

    @Override
    public boolean contains(String url) {
      //  return Paper.book("image").contains(url);
        return true;
    }

    @Override
    public void clear() {

    }

    @Override
    public File saveImage(String url, Bitmap bitmap) {

        if (!getImageDir().exists() && !getImageDir().mkdirs()) {
            throw new RuntimeException("Failed to create directory: " + getImageDir().toString());
        }

        final String fileFormat = url.contains(".jpg") ? ".jpg" : ".png";
        final File imageFile = new File(getImageDir(), SHA1(url) + fileFormat);
        FileOutputStream fos;

        try {
            fos = new FileOutputStream(imageFile);
            bitmap.compress(fileFormat.equals("jpg") ? Bitmap.CompressFormat.JPEG : Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            Timber.d("Failed to save image");
            return null;
        }


        PImage cachedImage = new PImage();
        cachedImage.setUrl(url);
        cachedImage.setPath(imageFile.toString());
        Paper.book("images").write(SHA1(url), cachedImage);


        return imageFile;
    }

    @Override
    public File getImageDir() {
        return new File(App.getInstance().getExternalFilesDir(null) + "/" + IMAGE_FOLDER_NAME);
    }

    @Override
    public String SHA1(String s) {
        MessageDigest m = null;

        try {
            m = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        m.update(s.getBytes(), 0, s.length());
        String hash = new BigInteger(1, m.digest()).toString(16);
        return hash;
    }

    @Override
    public float getSizeKb() {
        return getFileOrDirSize(getImageDir()) / 1024f;
    }

    @Override
    public void deleteFileOrDirRecursive(File fileOrDirectory) {

    }

    @Override
    public long getFileOrDirSize(File f) {
        long size = 0;
        if (f.isDirectory()) {
            for (File file : f.listFiles()) {
                size += getFileOrDirSize(file);
            }
        } else {
            size = f.length();
        }
        return size;
    }
}
