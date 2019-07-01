package com.example.jevodan.countries.mvp.model.api;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHolder {
    private static final ApiHolder ourInstance = new ApiHolder();

    public static ApiHolder getInstance() {
        return ourInstance;
    }

    private ApiData api;

    private ApiHolder() {

        Gson gson = new GsonBuilder()
              //  .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        api = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(ApiData.class);

    }

    public static ApiData getApi() {
        return getInstance().api;
    }

}
