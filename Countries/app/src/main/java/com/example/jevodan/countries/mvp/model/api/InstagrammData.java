package com.example.jevodan.countries.mvp.model.api;

import com.example.jevodan.countries.mvp.model.entity.InstDataSource;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InstagrammData {

    public static String token = "15867753227.16c3d6f.422da2250a9d49779076a27bfe3401a2";

    @GET("users/{userId}/media/recent/?access_token=" + token)
    Single<InstDataSource> getPhoto(
            @Path("userId") String userId

    );

}
