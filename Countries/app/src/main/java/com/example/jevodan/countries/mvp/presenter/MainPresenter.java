package com.example.jevodan.countries.mvp.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.jevodan.countries.mvp.model.entity.Country;
import com.example.jevodan.countries.mvp.model.entity.Datum;
import com.example.jevodan.countries.mvp.model.entity.Photo;
import com.example.jevodan.countries.mvp.model.entity.UserRepos;
import com.example.jevodan.countries.mvp.model.repo.CountriesRepo;
import com.example.jevodan.countries.mvp.model.repo.PhotoRepo;
import com.example.jevodan.countries.mvp.model.repo.UserRepo;
import com.example.jevodan.countries.mvp.view.CountryRowView;
import com.example.jevodan.countries.mvp.view.MainView;
import com.example.jevodan.countries.mvp.view.PhotoRowView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.subjects.PublishSubject;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> {

    public ICountryListPresenter getCountriesListPresenter() {
        return countryListPresenter;
    }

    public IReposListPresenter getReposListPresenter() {
        return reposListPresenter;
    }

    public IPhotoListPresenter getPhotoListPresenter() {
        return photoListPresenter;
    }

    class PhotoListPresenter implements IPhotoListPresenter{

        List<Datum> photos = new ArrayList<>();

        @Override
        public void bind(PhotoRowView view) {
            view.setTitle(photos.get(view.getPos()).getCaption().getText());
            view.setPictureUrl(photos.get(view.getPos()).getImages().getStandardResolution().getUrl());
        }

        @Override
        public int getCount() {
            return photos.size();
        }

    }

    class ReposListPresenter implements IReposListPresenter {

        List<UserRepos> countries = new ArrayList<>();

        @Override
        public void bind(CountryRowView view) {
            view.setTitle(countries.get(view.getPos()).getName());
            view.setCode(countries.get(view.getPos()).getFullName());
        }

        @Override
        public int getCount() {
            return countries.size();
        }

    }


    class CountryListPresenter implements ICountryListPresenter {

        PublishSubject<CountryRowView> publishSubject = PublishSubject.create();
        List<Country> countries = new ArrayList<>();

        @Override
        public void bind(CountryRowView view) {
            view.setTitle(countries.get(view.getPos()).getName());
            view.setCode(countries.get(view.getPos()).getCode());
        }

        @Override
        public int getCount() {
            return countries.size();
        }

        @Override
        public PublishSubject<CountryRowView> getClickSubject() {
            return publishSubject;
        }
    }


    private CountriesRepo repo;
    private UserRepo userRepo;
    private PhotoRepo photoRepo;
    private Scheduler mainThreadScheduler;
    private CountryListPresenter countryListPresenter = new CountryListPresenter();
    private ReposListPresenter reposListPresenter = new ReposListPresenter();
    private PhotoListPresenter photoListPresenter = new PhotoListPresenter();

    public MainPresenter(Scheduler mainThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.repo = new CountriesRepo();
        this.userRepo = new UserRepo();
        this.photoRepo = new PhotoRepo();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        // дай мне поток и выполни кусочек кода в нем.
        mainThreadScheduler.scheduleDirect(() -> {
            getViewState().init();

        });

        // loadCountries();
       // loadUser();
        loadPhoto();

        countryListPresenter.getClickSubject().subscribe(itemView -> {
            getViewState().showMessage(countryListPresenter.countries.get(itemView.getPos()).getName());
        });
    }

    @SuppressLint("CheckResult")
    private void loadPhoto() {
        photoRepo.getPhoto("15867753227")
                .observeOn(mainThreadScheduler)
                .subscribe(instagramm -> {
                    Log.d("444",instagramm.getData().get(0).getImages().getStandardResolution().getUrl() );
                    photoListPresenter.photos.clear();
                    photoListPresenter.photos.addAll(instagramm.getData());
                    getViewState().updateList();
                 //   Log.d("5555",instagramm.getProfilePicture() + instagramm.getUsername());
                  //  getViewState().showPicture(instagramm.getData().get(0).getImages().getThumbnail().getUrl());
                });
    }

    private void loadCountries() {
        repo.getCountries()
                .observeOn(mainThreadScheduler)
                .subscribe(countries -> {
                    Timber.d(String.valueOf(countries.size()));
                    countryListPresenter.countries.clear();
                    countryListPresenter.countries.addAll(countries);
                    getViewState().updateList();
                });
    }

    private void loadUser() {
        userRepo.getUser("googlesamples")

                .observeOn(mainThreadScheduler)
                .subscribe(user -> {
                    getViewState().showUser(user.getLogin(), user.getReposUrl());
                });
    }

    public void loadUserRepo(String url) {
        userRepo.getUserRepos(url)
                .observeOn(mainThreadScheduler)
                .subscribe(repos -> {
                    reposListPresenter.countries.clear();
                    reposListPresenter.countries.addAll(repos);
                    getViewState().updateList();
                    Timber.d(String.valueOf(repos.size()));
                    Log.d("555", String.valueOf(repos.size()));
                    loadPhoto();
                });
    }

}
