package com.example.jevodan.instagram.mvp.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.jevodan.instagram.mvp.view.ActivityView;
import com.example.jevodan.instagram.navigation.Screens;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import ru.terrakok.cicerone.Router;


@InjectViewState
public class MainPresenter extends MvpPresenter<ActivityView> {

    private Scheduler mainThread;

    @Inject
    Router router;

    /**
     * Конструктор класса
     *
     * @param mainThread
     */
    public MainPresenter(Scheduler mainThread) {
        this.mainThread = mainThread;
    }

    /**
     * Метод первого запуска Активити
     */
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        router.replaceScreen(new Screens.MainScreen());
    }
}
