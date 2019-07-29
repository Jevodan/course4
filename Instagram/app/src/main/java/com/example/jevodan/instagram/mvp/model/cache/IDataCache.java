package com.example.jevodan.instagram.mvp.model.cache;

import com.example.jevodan.instagram.mvp.model.entity.Datum;
import com.example.jevodan.instagram.mvp.model.entity.InstDataSource;
import com.example.jevodan.instagram.mvp.model.entity.User;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Интерфейс для работы с кешем данным,получаемых через АПИ
 */
public interface IDataCache {

    Single<InstDataSource> getData(String userId, Boolean chosen);

    Completable putUser(User user);

    Completable putImages(List<Datum> datum, String idUser);

    Completable saveNewFavor(String name);

    Boolean getCacheFavor(String text);
}
