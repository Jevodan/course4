package com.example.jevodan.instagram.mvp.presenter;


import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.jevodan.instagram.mvp.model.entity.Datum;
import com.example.jevodan.instagram.mvp.model.repo.IDataRepo;
import com.example.jevodan.instagram.mvp.view.MainView;
import com.example.jevodan.instagram.mvp.view.PhotoRowView;
import com.example.jevodan.instagram.navigation.Screens;
import com.example.jevodan.instagram.tools.Constants;
import com.example.jevodan.instagram.ui.adapter.PhotoAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.subjects.PublishSubject;
import ru.terrakok.cicerone.Router;

@InjectViewState
public class MainFragmentPresenter extends MvpPresenter<MainView> {

    @Inject
    IDataRepo dataRepo;

    @Inject
    Router router;

    private MainFragmentPresenter.PhotoListPresenter photoListPresenter = new MainFragmentPresenter.PhotoListPresenter();
    private Scheduler mainThreadScheduler;


    public IPhotoListPresenter getPhotoListPresenter() {
        return photoListPresenter;
    }

    /**
     * Внутренний класс для адаптивного адаптера
     */
    class PhotoListPresenter implements IPhotoListPresenter {

        List<Datum> photos = new ArrayList<>();
        PublishSubject<PhotoRowView> clickSubject = PublishSubject.create();
        PublishSubject<PhotoRowView> clickSubjectFavor = PublishSubject.create();

        /**
         * Вызываем метод из адаптера {@link PhotoAdapter#onBindViewHolder}
         *
         * @param view - Вью - позиция / строка
         */
        @Override
        public void bind(PhotoRowView view) {
            view.setTitle(photos.get(view.getPos()).getCaption().getText());
            view.setPictureUrl(photos.get(view.getPos()).getImages().getStandardResolution().getUrl());
            view.setFavor(photos.get(view.getPos()).getCaption().getFavor());
        }

        /**
         * @return - возвращает размер массива переданных данных
         */
        @Override
        public int getCount() {
            return photos.size();
        }

        /**
         * @return - возвращаем клик по сердечку
         */
        @Override
        public PublishSubject<PhotoRowView> getClickFavor() {
            return clickSubjectFavor;
        }

        /**
         * @return - возвращаем клик по позиции
         */
        @Override
        public PublishSubject<PhotoRowView> getClickSubject() {
            return clickSubject;
        }

    }

    /**
     * Конструктор класса
     *
     * @param mainThreadScheduler
     */
    public MainFragmentPresenter(Scheduler mainThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
    }


    /**
     * Метод первого запуска фрагмента
     */
    @SuppressLint("CheckResult")
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
        loadData(Constants.USER_ID, true, false);

        photoListPresenter.getClickSubject().subscribe(dataRow -> {
            router.navigateTo(new Screens.DetailScreen(
                    photoListPresenter.photos.get(dataRow.getPos()).getImages().getStandardResolution().getUrl(),
                    photoListPresenter.photos.get(dataRow.getPos()).getCaption().getText()));
        });


        photoListPresenter.getClickFavor().subscribe(data -> {
            String name = photoListPresenter.photos.get(data.getPos()).getCaption().getText();
            dataRepo.saveFavor(name).observeOn(mainThreadScheduler)
                    .subscribe();

            getViewState().showPhoto(name);
        });
    }

    /**
     * Загрузка данных с сервера
     *
     * @param userId     - id пользователя
     * @param net        - передаваемый из вью флаг сети
     * @param onlyChosen - флаг подгрузки только
     *                   избранных, или всех фотографий
     */
    @SuppressLint("CheckResult")
    public void loadData(String userId, Boolean net, Boolean onlyChosen) {
        getViewState().showLoading();
        dataRepo.getPhoto(userId, net, onlyChosen)
                .observeOn(mainThreadScheduler)
                .subscribe(data -> {
                    getViewState().showMessage(data.getData().get(0).getUser());
                    photoListPresenter.photos.clear();
                    photoListPresenter.photos.addAll(data.getData());
                    getViewState().updateList();
                    getViewState().hideLoading();
                });
    }

}
