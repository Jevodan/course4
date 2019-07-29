package com.example.jevodan.countries.mvp.presenter;

import com.example.jevodan.countries.mvp.view.CountryRowView;

import io.reactivex.subjects.PublishSubject;

public interface ICountryListPresenter {

    void bind(CountryRowView view);
    int getCount();
    PublishSubject<CountryRowView> getClickSubject();

}
