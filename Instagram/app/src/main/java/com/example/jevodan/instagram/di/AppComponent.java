package com.example.jevodan.instagram.di;

import com.example.jevodan.instagram.mvp.presenter.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RepoModule.class})
public interface AppComponent {

    void inject(MainPresenter presenter);

}
