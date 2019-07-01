package com.example.jevodan.countries.mvp.model.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHolderInstagramm {
    private static final ApiHolderInstagramm ourInstance = new ApiHolderInstagramm();

    public static ApiHolderInstagramm getInstance() {
        return ourInstance;
    }

    private InstagrammData api;

    private ApiHolderInstagramm() {

        Gson gson = new GsonBuilder()
              //  .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        api = new Retrofit.Builder()
                .baseUrl("https://api.instagram.com/v1/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(InstagrammData.class);

    }

    public static InstagrammData getApi() {
        return getInstance().api;
    }

}
