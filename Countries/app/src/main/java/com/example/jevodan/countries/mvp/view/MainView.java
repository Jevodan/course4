package com.example.jevodan.countries.mvp.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import com.example.jevodan.countries.mvp.model.entity.Datum;
import com.example.jevodan.countries.mvp.model.entity.InstDataSource;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface MainView extends MvpView {

    void updateList();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showMessage(String text);

    void init();

    void showUser(String login, String url);

    void showPicture(String url);
}
