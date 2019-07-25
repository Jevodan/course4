package com.example.jevodan.instagram.mvp.model.cache;

import com.example.jevodan.instagram.mvp.model.entity.Caption;
import com.example.jevodan.instagram.mvp.model.entity.Datum;
import com.example.jevodan.instagram.mvp.model.entity.Images;
import com.example.jevodan.instagram.mvp.model.entity.InstDataSource;
import com.example.jevodan.instagram.mvp.model.entity.StandardResolution;
import com.example.jevodan.instagram.mvp.model.entity.User;
import com.example.jevodan.instagram.mvp.model.entity.room.db.Database;
import com.example.jevodan.instagram.mvp.model.entity.room.tables.RoomPhoto;
import com.example.jevodan.instagram.mvp.model.entity.room.tables.RoomUser;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class RoomCache implements IDataCache {

    @Override
    public Single<InstDataSource> getData(String userId) {
        return Single.create(emitter -> {
            InstDataSource instDataSource = new InstDataSource();
            RoomUser roomUser = Database.getInstance().getUserDao().findById(userId);
            List<RoomPhoto> roomPhoto = Database.getInstance().getPhotoDao().getAll(userId);
            List<Datum> listData = new ArrayList<>();
            for (RoomPhoto roomPhoto1 : roomPhoto) {
                Datum datum = new Datum();
                User user = new User();
                user.setFullName(roomUser.getLogin());
                user.setId(roomUser.getId());
                user.setProfilePicture(roomUser.getAvatarUrl());
                user.setUsername(roomUser.getUsername());
                Images images = new Images();
                StandardResolution standardResolution = new StandardResolution();
                Caption caption = new Caption();
                caption.setText(roomPhoto1.getName());
                standardResolution.setUrl(roomPhoto1.getUrlPhoto());
                images.setStandardResolution(standardResolution);
                datum.setImages(images);
                datum.setCaption(caption);
                datum.setUser(user);
                listData.add(datum);
            }
            instDataSource.setData(listData);
            emitter.onSuccess(instDataSource);
        }).subscribeOn(Schedulers.io()).cast(InstDataSource.class);

    }

    @Override
    public Completable putUser(User user) {
        return Completable.fromAction(() -> {
            RoomUser roomUser = Database.getInstance().getUserDao().findById(user.getId());
            if (roomUser == null) {
                roomUser = new RoomUser();
                roomUser.setId(user.getId());
                roomUser.setAvatarUrl(user.getProfilePicture());
                roomUser.setUsername(user.getUsername());
                roomUser.setLogin(user.getFullName());
                Database.getInstance().getUserDao().insert(roomUser);
            }

        });
    }

    @Override
    public Completable putImages(List<Datum> data, String idUser) {
        return Completable.fromAction(() -> {
            String caption;
            String url;
            for (Datum datum : data) {
                caption = datum.getCaption().getText();
                url = datum.getImages().getThumbnail().getUrl();
                RoomPhoto roomPhoto = Database.getInstance()
                        .getPhotoDao()
                        .findByName(caption);
                if (roomPhoto == null) {
                    roomPhoto = new RoomPhoto();
                    roomPhoto.setUser(idUser);
                    roomPhoto.setChosen(false);
                    roomPhoto.setName(caption);
                    roomPhoto.setUrlPhoto(url);
                    Database.getInstance().getPhotoDao().insert(roomPhoto);
                }
            }
        });
    }

}
