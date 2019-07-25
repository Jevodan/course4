package com.example.jevodan.instagram.mvp.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.jevodan.instagram.mvp.model.entity.Datum;
import com.example.jevodan.instagram.mvp.model.repo.DataRepo;
import com.example.jevodan.instagram.mvp.model.repo.IDataRepo;
import com.example.jevodan.instagram.mvp.view.MainView;
import com.example.jevodan.instagram.mvp.view.PhotoRowView;
import com.example.jevodan.instagram.tools.Constants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    IDataRepo dataRepo;
    private PhotoListPresenter photoListPresenter = new PhotoListPresenter();
    Scheduler mainThreadScheduler;

    public IPhotoListPresenter getPhotoListPresenter() {
        return photoListPresenter;
    }

    public void showChosen() {
    }

    class PhotoListPresenter implements IPhotoListPresenter {

        List<Datum> photos = new ArrayList<>();

        @Override
        public void bind(PhotoRowView view) {
            view.setTitle(photos.get(view.getPos()).getCaption().getText());
            view.setPictureUrl(photos.get(view.getPos()).getImages().getStandardResolution().getUrl());
        }

        @Override
        public int getCount() {
            return photos.size();
        }

    }


    public MainPresenter(Scheduler mainThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
    }


    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        mainThreadScheduler.scheduleDirect(() -> getViewState().init());
        loadData(Constants.USER_ID, true);
    }

    @SuppressLint("CheckResult")
    public void loadData(String userId, Boolean net) {
        getViewState().showLoading();
        dataRepo.getPhoto(userId, net)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    getViewState().showMessage(data.getData().get(0).getUser());
                    photoListPresenter.photos.clear();
                    photoListPresenter.photos.addAll(data.getData());
                    getViewState().updateList();
                    getViewState().hideLoading();
                });
    }

}
