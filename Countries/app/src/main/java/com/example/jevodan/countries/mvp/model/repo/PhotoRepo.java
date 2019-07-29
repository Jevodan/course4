package com.example.jevodan.countries.mvp.model.repo;

import android.util.Log;

import com.example.jevodan.countries.mvp.model.api.ApiHolder;
import com.example.jevodan.countries.mvp.model.api.ApiHolderInstagramm;
import com.example.jevodan.countries.mvp.model.api.INetworkStatus;
import com.example.jevodan.countries.mvp.model.entity.Caption;
import com.example.jevodan.countries.mvp.model.entity.Datum;
import com.example.jevodan.countries.mvp.model.entity.Images;
import com.example.jevodan.countries.mvp.model.entity.InstDataSource;
import com.example.jevodan.countries.mvp.model.entity.Owner;
import com.example.jevodan.countries.mvp.model.entity.Photo;
import com.example.jevodan.countries.mvp.model.entity.StandardResolution;
import com.example.jevodan.countries.mvp.model.entity.Thumbnail;
import com.example.jevodan.countries.mvp.model.entity.room.RoomPhoto;
import com.example.jevodan.countries.mvp.model.entity.room.db.Database;
import com.example.jevodan.countries.ui.NetworkStatus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class PhotoRepo {

    INetworkStatus networkStatus = new NetworkStatus();

    public Single<InstDataSource> getPhoto(String username) {
        if (networkStatus.isOnline()) {
            return ApiHolderInstagramm.getApi().getPhoto(username)
                    .subscribeOn(Schedulers.io())
                    .map(user -> {
                        RoomPhoto roomPhoto = Database.getInstance().getPhotoDao()
                                .findByLogin(username);

                        if (roomPhoto == null) {
                            roomPhoto = new RoomPhoto();
                            roomPhoto.setName(username);
                        }

                        roomPhoto.setUrlPhoto(user.getData().get(0).getImages().getThumbnail().getUrl());
                        Log.d("444", "loading");

                        Database.getInstance().getPhotoDao()
                                .insert(roomPhoto);

                        return user;
                    });
        } else {
            return Single.create(emitter -> {
                Log.d("444", "getting");
                RoomPhoto roomUser = Database.getInstance().getPhotoDao()
                        .findByLogin(username);

                if (roomUser == null) {
                    emitter.onError(new RuntimeException("No such user in cache"));
                } else {

                    //    view.setPictureUrl(photos.get(view.getPos()).getImages().getStandardResolution().getUrl());

                    InstDataSource instDataSource = new InstDataSource();
                    List<Datum> listData = new ArrayList<>();
                    Datum datum = new Datum();
                    Images images = new Images();
                    StandardResolution standardResolution = new StandardResolution();
                    Caption caption = new Caption();
                    caption.setText(roomUser.getName());
                    standardResolution.setUrl(roomUser.getUrlPhoto());
                    images.setStandardResolution(standardResolution);
                    datum.setImages(images);
                    datum.setCaption(caption);
                    listData.add(datum);
                    instDataSource.setData(listData);


                    //  roomPhoto.setUrlPhoto(user.getData().get(0).getImages().getThumbnail().getUrl());
                    emitter.onSuccess(instDataSource);
                }
            }).subscribeOn(Schedulers.io()).cast(InstDataSource.class);
        }
    }

}
