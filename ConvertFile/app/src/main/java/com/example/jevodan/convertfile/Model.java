package com.example.jevodan.convertfile;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import io.reactivex.Completable;
import io.reactivex.schedulers.Schedulers;
import static java.lang.Thread.sleep;


public class Model {

    String fileName = "new.png";
    String path;
    FileOutputStream fos;

    public Model(String path) {
        this.path = path;

    }

    public Completable convertJPGtoPNG(String convertedFile) {
        return Completable.fromAction(() -> convert(convertedFile)).subscribeOn(Schedulers.io());
    }

    private void convert(String convertedFile) {
        try {
            fos = new FileOutputStream(path + "/" + fileName);
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            sleep(3000);
            Bitmap bitmap = BitmapFactory.decodeFile(path + "/" + convertedFile + ".jpg");
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
