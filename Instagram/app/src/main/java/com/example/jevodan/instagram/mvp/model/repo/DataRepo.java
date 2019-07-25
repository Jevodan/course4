package com.example.jevodan.instagram.mvp.model.repo;

import android.util.Log;
import com.example.jevodan.instagram.mvp.model.api.ApiData;
import com.example.jevodan.instagram.mvp.model.cache.IDataCache;
import com.example.jevodan.instagram.mvp.model.entity.InstDataSource;
import com.example.jevodan.instagram.ui.net.INetworkStatus;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class DataRepo implements IDataRepo {

    private INetworkStatus networkStatus;
    private ApiData apiData;
    private IDataCache dataCache;

    public DataRepo(INetworkStatus networkStatus, ApiData apiData, IDataCache dataCache) {
        this.networkStatus = networkStatus;
        this.apiData = apiData;
        this.dataCache = dataCache;
    }

    public Single<InstDataSource> getPhoto(String userId, Boolean net) {
        if (networkStatus.isOnline() && net) {
            return apiData.getData(userId)
                    .subscribeOn(Schedulers.io())
                    .map(data -> {
                        dataCache.putUser(data.getData().get(0).getUser()).subscribe();
                        dataCache.putImages(data.getData(), userId).subscribe();
                        return data;
                    });
        } else {
            return dataCache.getData(userId);

        }
    }

}
