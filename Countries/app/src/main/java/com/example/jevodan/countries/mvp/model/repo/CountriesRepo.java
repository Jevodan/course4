package com.example.jevodan.countries.mvp.model.repo;

import com.example.jevodan.countries.mvp.model.api.DataSource;
import com.example.jevodan.countries.mvp.model.entity.Country;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class CountriesRepo {

    private DataSource dataSource;

    public  CountriesRepo(){
        this.dataSource = new DataSource();
    }

    public Single<List<Country>> getCountries(){
        return Single.fromCallable(()-> dataSource.loadCountries()).subscribeOn(Schedulers.io());
    }
}
