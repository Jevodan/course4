package com.example.jevodan.instagram.mvp.presenter;


import com.example.jevodan.instagram.mvp.view.PhotoRowView;

public interface IPhotoListPresenter {

    void bind(PhotoRowView view);
    int getCount();

}
