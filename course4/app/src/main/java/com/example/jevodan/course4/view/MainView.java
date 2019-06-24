package com.example.jevodan.course4.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(value = AddToEndSingleStrategy.class)
public interface MainView extends MvpView {

    void setButtonText1(Integer value);
    void setButtonText2(Integer value);
    void setButtonText3(Integer value);

}
