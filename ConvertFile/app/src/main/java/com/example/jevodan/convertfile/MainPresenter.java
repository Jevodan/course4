package com.example.jevodan.convertfile;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> implements IMainPresenter {

    Model model;

    public MainPresenter(String pathName) {
        this.model = new Model(pathName);
    }

    @SuppressLint("CheckResult")
    @Override
    public void goConvert(String fileName) {
        getViewState().progressShow();
        model.convertJPGtoPNG(fileName).
                observeOn(AndroidSchedulers.mainThread()).
                subscribe(() -> {
                    getViewState().showImage();
                    getViewState().progressHide();
                });
    }
}
