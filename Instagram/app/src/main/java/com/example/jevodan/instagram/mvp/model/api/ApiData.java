package com.example.jevodan.instagram.mvp.model.api;

import com.example.jevodan.instagram.mvp.model.entity.InstDataSource;
import com.example.jevodan.instagram.mvp.model.entity.Owner;
import com.example.jevodan.instagram.tools.Constants;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiData {

    @GET("users/{userId}/media/recent/?access_token=" + Constants.TOKEN)
    Single<InstDataSource> getData(
            @Path("userId") String userId
    );

    @GET("users/{user}")
    Single<Owner> getUser(@Path("user") String username);


}
