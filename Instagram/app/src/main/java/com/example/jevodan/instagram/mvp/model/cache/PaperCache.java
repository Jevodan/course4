package com.example.jevodan.instagram.mvp.model.cache;

import com.example.jevodan.instagram.mvp.model.entity.Caption;
import com.example.jevodan.instagram.mvp.model.entity.Datum;
import com.example.jevodan.instagram.mvp.model.entity.Images;
import com.example.jevodan.instagram.mvp.model.entity.InstDataSource;
import com.example.jevodan.instagram.mvp.model.entity.User;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

public class PaperCache implements IDataCache {


    @Override
    public Single<InstDataSource> getData(String userId, Boolean chosen) {
        return null;
    }

    @Override
    public Completable putUser(User user) {
        return null;
    }

    @Override
    public Completable putImages(List<Datum> datum, String idUser) {
        return null;
    }

    @Override
    public Completable saveNewFavor(String name) {
        return null;
    }

    @Override
    public Boolean getCacheFavor(String text) {
        return null;
    }


}
