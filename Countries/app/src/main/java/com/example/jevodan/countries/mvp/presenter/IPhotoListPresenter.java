package com.example.jevodan.countries.mvp.presenter;

import com.example.jevodan.countries.mvp.view.CountryRowView;
import com.example.jevodan.countries.mvp.view.PhotoRowView;

public interface IPhotoListPresenter {

    void bind(PhotoRowView view);
    int getCount();

}
