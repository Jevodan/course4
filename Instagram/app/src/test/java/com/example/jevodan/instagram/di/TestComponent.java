package com.example.jevodan.instagram.di;


import com.example.jevodan.instagram.di.TestRepoModule;
import com.example.jevodan.instagram.mvp.presenter.MainFragmentPresenter;
import com.example.jevodan.instagram.MainFragmentPresenterTest;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TestRepoModule.class, TestCiceroneModule.class})
public interface TestComponent {

    void inject(MainFragmentPresenter presenter);

    void inject(MainFragmentPresenterTest mainFragmentPresenterTest);

}
