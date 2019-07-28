package com.example.jevodan.instagram.mvp.view;

import com.arellomobile.mvp.MvpView;

public interface DetailView extends MvpView {

    void setPicture(String picture);
    void showMessage(String title);

}
