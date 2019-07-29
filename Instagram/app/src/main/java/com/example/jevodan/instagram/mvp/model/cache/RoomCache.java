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

    /**
     * Метод получения данных из локальной базы Room {@link Database}
     *
     * @param userId - id пользователя
     * @param chosen - отметка - избранное для фотографий
     * @return - возвращаем объект данных {@link InstDataSource}
     */
    @Override
    public Single<InstDataSource> getData(String userId, Boolean chosen) {
        return Single.create(emitter -> {
            List<RoomPhoto> roomPhoto;
            InstDataSource instDataSource = new InstDataSource();
            RoomUser roomUser = Database.getInstance().getUserDao().findById(userId);
            if (chosen)
                roomPhoto = Database.getInstance().getPhotoDao().findChosen(userId);
            else
                roomPhoto = Database.getInstance().getPhotoDao().getAll(userId);
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
                caption.setFavor(roomPhoto1.isChosen());
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

    /**
     * Метод для записи данный в Room таблицы  {@link RoomUser}
     *
     * @param user - объект User {@link User}
     */
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


    /**
     * Метод для записи данный в Room таблицы  {@link RoomPhoto}
     *
     * @param data   - основная часть получаемого
     *               из интернета объекта {@link Datum}
     * @param idUser - id пользователя
     */
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

    /**
     * Метод для изменения флага во
     * внутренней базе "любимые фотографии"
     *
     * @param name - название фотографии
     * @return
     */
    @Override
    public Completable saveNewFavor(String name) {
        return Completable.fromAction(() -> {
            RoomPhoto roomPhoto = Database.getInstance()
                    .getPhotoDao()
                    .findByName(name);
            if (roomPhoto != null) {
                roomPhoto.setChosen(!roomPhoto.isChosen());
                Database.getInstance().getPhotoDao().update(roomPhoto);
            }
        }).subscribeOn(Schedulers.io());
    }

    /**
     * Метод возвращает флаг
     * "избранная фотография" из внутренней БД
     *
     * @param text - название фотографии
     * @return
     */
    @Override
    public Boolean getCacheFavor(String text) {
        RoomPhoto roomPhoto = Database.getInstance()
                .getPhotoDao()
                .findByName(text);
        if (roomPhoto != null)
            return roomPhoto.isChosen();
        else
            return false;
    }

}
