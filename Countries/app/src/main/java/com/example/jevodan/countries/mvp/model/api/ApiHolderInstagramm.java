package com.example.jevodan.countries.mvp.model.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

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

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Log.d("000",message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addNetworkInterceptor(loggingInterceptor)
                .build();

        api = new Retrofit.Builder()
                .client(client)
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
