package ru.geekbrains.gb_android_libraries.di;

import dagger.Component;
import ru.geekbrains.gb_android_libraries.mvp.presenter.MainPresenter;
import ru.geekbrains.gb_android_libraries.ui.activity.MainActivity;

import javax.inject.Singleton;

@Singleton
@Component(modules = {RepoModule.class, UtilsModule.class, ImageLoaderModule.class})
public interface AppComponent {

    void inject(MainPresenter mainPresenter);

    void inject(MainActivity mainActivity);

}
