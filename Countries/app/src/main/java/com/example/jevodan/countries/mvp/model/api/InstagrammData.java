package com.example.jevodan.countries.mvp.model.api;

import com.example.jevodan.countries.mvp.model.entity.TestInstData;

import io.reactivex.Single;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface InstagrammData {

   // https://api.instagram.com/v1/users/self/?access_token=15867753227.16c3d6f.422da2250a9d49779076a27bfe3401a2
    public static String token = "15867753227.16c3d6f.422da2250a9d49779076a27bfe3401a2";

   // @GET("users/{userId}/?access_token=" + token)

    @GET("users/{userId}?access_token=15867753227.16c3d6f.422da2250a9d49779076a27bfe3401a2")
    Single<TestInstData> getPhoto(
            @Path("userId") String userId

    );

}
