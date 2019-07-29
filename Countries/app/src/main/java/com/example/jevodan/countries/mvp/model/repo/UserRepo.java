package com.example.jevodan.countries.mvp.model.repo;

import com.example.jevodan.countries.mvp.model.api.ApiHolder;
import com.example.jevodan.countries.mvp.model.api.ApiHolderInstagramm;
import com.example.jevodan.countries.mvp.model.entity.InstDataSource;
import com.example.jevodan.countries.mvp.model.entity.Owner;
import com.example.jevodan.countries.mvp.model.entity.UserRepos;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class UserRepo {

    public Single<Owner> getUser(String username) {
        return ApiHolder.getApi().getUser(username).subscribeOn(Schedulers.io());
    }

    public Single<List<UserRepos>> getUserRepos(String url) {
        return ApiHolder.getApi().getUserRepos(url).subscribeOn(Schedulers.io());
    }

    public Single<InstDataSource> getPhoto(String userId) {
        return ApiHolderInstagramm.getApi().getPhoto(userId).subscribeOn(Schedulers.io());
    }

}
