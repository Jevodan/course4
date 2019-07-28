package com.example.jevodan.instagram.mvp.presenter;


import com.example.jevodan.instagram.mvp.view.PhotoRowView;

import io.reactivex.subjects.PublishSubject;

public interface IPhotoListPresenter {

    void bind(PhotoRowView view);
    int getCount();
    PublishSubject<PhotoRowView> getClickFavor();
    PublishSubject<PhotoRowView> getClickSubject();

}
