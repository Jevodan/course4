package com.example.jevodan.instagram.mvp.model.repo;

import android.util.Log;

import com.example.jevodan.instagram.mvp.model.api.ApiData;
import com.example.jevodan.instagram.mvp.model.cache.IDataCache;
import com.example.jevodan.instagram.mvp.model.entity.Datum;
import com.example.jevodan.instagram.mvp.model.entity.InstDataSource;
import com.example.jevodan.instagram.ui.net.INetworkStatus;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class DataRepo implements IDataRepo {

    private INetworkStatus networkStatus;
    private ApiData apiData;
    private IDataCache dataCache;

    /**
     * Конструктор класса
     *
     * @param networkStatus - переменная класса для статуса сети
     * @param apiData       - данные,получаемые через Апи
     * @param dataCache     - переменная класса кеширования
     */
    public DataRepo(INetworkStatus networkStatus, ApiData apiData, IDataCache dataCache) {
        this.networkStatus = networkStatus;
        this.apiData = apiData;
        this.dataCache = dataCache;
    }

    /**
     * Метод отдает данные приложению
     * и проверряет откуда их брать: из
     * сети,или из кеша
     *
     * @param userId - id  пользователя
     * @param net - флаг сети, установленный пользователем
     *            (при использовании данные подгружаются из кеша)
     * @param chosen - флаг папки "только избранные фотографии"
     * @return - возвращает данные объект {@link InstDataSource}
     */
    public Single<InstDataSource> getPhoto(String userId, Boolean net, Boolean chosen) {
        if (networkStatus.isOnline() && net) {
            return apiData.getData(userId)
                    .subscribeOn(Schedulers.io())
                    .map(data -> {
                        dataCache.putUser(data.getData().get(0).getUser()).subscribe();
                        dataCache.putImages(data.getData(), userId).subscribe();
                        for (Datum datum : data.getData()) {
                            datum.getCaption().setFavor(dataCache.getCacheFavor(datum.getCaption().getText()));
                        }
                        return data;
                    });
        } else {
            return dataCache.getData(userId, chosen);

        }
    }

    /**
     * Метод записывает в кеш измененные
     * данные об фотографии (избранное, или нет)
     * @param name
     * @return
     */
    @Override
    public Completable saveFavor(String name) {
        return dataCache.saveNewFavor(name);
    }

}
