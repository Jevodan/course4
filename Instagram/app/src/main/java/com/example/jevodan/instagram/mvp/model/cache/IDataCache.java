package com.example.jevodan.instagram.mvp.model.cache;

import com.example.jevodan.instagram.mvp.model.entity.Caption;
import com.example.jevodan.instagram.mvp.model.entity.Datum;
import com.example.jevodan.instagram.mvp.model.entity.Images;
import com.example.jevodan.instagram.mvp.model.entity.InstDataSource;
import com.example.jevodan.instagram.mvp.model.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface IDataCache {

    Single<InstDataSource> getData(String userId);

    Completable putUser(User user);

    Completable putImages(List<Datum> datum, String idUser);

}
