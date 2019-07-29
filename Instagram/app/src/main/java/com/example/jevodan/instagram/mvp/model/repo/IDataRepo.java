package com.example.jevodan.instagram.mvp.model.repo;

import com.example.jevodan.instagram.mvp.model.entity.InstDataSource;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface IDataRepo {

    Single<InstDataSource> getPhoto(String userId, Boolean net, Boolean chosen);

    Completable saveFavor(String name);
}
