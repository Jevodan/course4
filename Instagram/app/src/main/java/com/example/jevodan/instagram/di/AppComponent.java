package com.example.jevodan.instagram.di;

import com.example.jevodan.instagram.di.modules.AppModule;
import com.example.jevodan.instagram.di.modules.CiceroneModule;
import com.example.jevodan.instagram.di.modules.RepoModule;
import com.example.jevodan.instagram.mvp.presenter.DetailFragmentPresenter;
import com.example.jevodan.instagram.mvp.presenter.MainFragmentPresenter;
import com.example.jevodan.instagram.mvp.presenter.MainPresenter;
import com.example.jevodan.instagram.ui.activity.MainActivity;
import com.example.jevodan.instagram.ui.fragment.DetailFragment;
import com.example.jevodan.instagram.ui.fragment.MainFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RepoModule.class, CiceroneModule.class})
public interface AppComponent {

    void inject(MainPresenter presenter);
    void inject(MainFragmentPresenter presenter);
    void inject(DetailFragmentPresenter presenter);

    void inject(MainActivity mainActivity);
    void inject(MainFragment mainFragment);
    void inject(DetailFragment detailFragment);

}
