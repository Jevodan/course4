package com.example.jevodan.course4.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.jevodan.course4.view.MainView;
import com.example.jevodan.course4.model.Model;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    private Model model;

    public MainPresenter() {
        this.model = new Model();
    }

    @SuppressLint("CheckResult")
    public void counterClick1(int index) {
        model.getAt(index)
                .observeOn(Schedulers.computation())
                .map(integer -> {
                    calcValue(index, integer);
                    return integer;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> MainPresenter.this.getViewState().setButtonText1(integer));
    }

    @SuppressLint("CheckResult")
    public void counterClick2(int index) {
        model.getAt(index)
                .observeOn(Schedulers.computation())
                .map(integer -> {
                    calcValue(index, integer);
                    return integer;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> MainPresenter.this.getViewState().setButtonText2(integer));
    }

    @SuppressLint("CheckResult")
    public void counterClick3(int index) {
        model.getAt(index)
                .observeOn(Schedulers.computation())
                .map(integer -> {
                    calcValue(index, integer);
                    return integer;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> MainPresenter.this.getViewState().setButtonText3(integer));
    }

    @Override
    protected void onFirstViewAttach() {
        counterClick1(0);
        counterClick2(1);
        counterClick3(2);
        super.onFirstViewAttach();
    }

    private void calcValue(int index, int integer) {
        model.setAt(index, integer + 1);
    }


}
