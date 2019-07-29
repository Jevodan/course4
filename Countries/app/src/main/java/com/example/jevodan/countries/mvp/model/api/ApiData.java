package com.example.jevodan.countries.mvp.model.api;

import com.example.jevodan.countries.mvp.model.entity.Owner;
import com.example.jevodan.countries.mvp.model.entity.UserRepos;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

public interface ApiData {

  //  @GET("/users{user}/repos")
//    Single<UserRepos> getUserRepo(@Path ("user") String username);

    @GET("users/{user}")
    Single<Owner> getUser(@Path("user") String username);

    @GET
    Single<List<UserRepos>> getUserRepos(@Url String url);

}
