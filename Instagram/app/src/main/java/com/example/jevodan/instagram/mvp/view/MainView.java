package com.example.jevodan.instagram.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.jevodan.instagram.mvp.model.entity.Datum;
import com.example.jevodan.instagram.mvp.model.entity.User;

import java.util.List;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface MainView extends MvpView {

    void showMessage(User user);

    void init();

    void showPhoto(List<Datum> data);

    void updateList();

    void showLoading();

    void hideLoading();

}
