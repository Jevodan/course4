package com.example.jevodan.instagram.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.jevodan.instagram.mvp.view.DetailView;

import javax.inject.Inject;
import io.reactivex.Scheduler;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class DetailFragmentPresenter extends MvpPresenter<DetailView> {

    @Inject
    Router router;

    private String title;
    private String picture;
    Scheduler mainThreadScheduler;

    /**
     * Конструктор класса
     * @param schedulersMainThread
     * @param picture - детальная картинка
     * @param title - название картинки
     */
    public DetailFragmentPresenter(Scheduler schedulersMainThread, String picture, String title) {
        this.mainThreadScheduler = schedulersMainThread;
        this.title = title;
        this.picture = picture;
    }

    /**
     * Метод для первого запуска фрагмента
     */
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().setPicture(picture);
        getViewState().showMessage(title);
    }


    public void backClick() {
        router.exit();
    }
}
