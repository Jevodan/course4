package com.example.jevodan.countries.mvp.presenter;

import android.annotation.SuppressLint;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.jevodan.countries.mvp.model.entity.Country;
import com.example.jevodan.countries.mvp.model.entity.Owner;
import com.example.jevodan.countries.mvp.model.entity.TestInstData;
import com.example.jevodan.countries.mvp.model.entity.UserRepos;
import com.example.jevodan.countries.mvp.model.repo.CountriesRepo;
import com.example.jevodan.countries.mvp.model.repo.UserRepo;
import com.example.jevodan.countries.mvp.view.CountryRowView;
import com.example.jevodan.countries.mvp.view.MainView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
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

    class ReposListPresenter implements IReposListPresenter {

        PublishSubject<CountryRowView> publishSubject = PublishSubject.create();
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
    private Scheduler mainThreadScheduler;
    private CountryListPresenter countryListPresenter = new CountryListPresenter();
    private ReposListPresenter reposListPresenter = new ReposListPresenter();

    public MainPresenter(Scheduler mainThreadScheduler) {
        this.mainThreadScheduler = mainThreadScheduler;
        this.repo = new CountriesRepo();
        this.userRepo = new UserRepo();
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        // дай мне поток и выполни кусочек кода в нем.
        mainThreadScheduler.scheduleDirect(() -> {
            getViewState().init();

        });

        //  loadCountries();
        loadUser();

        countryListPresenter.getClickSubject().subscribe(itemView -> {
            getViewState().showMessage(countryListPresenter.countries.get(itemView.getPos()).getName());
        });
    }

    @SuppressLint("CheckResult")
    private void loadPhoto() {
        userRepo.getPhoto("15867753227")
                .observeOn(mainThreadScheduler)
                .subscribe(instagramm -> {
                    Log.d("5555",instagramm.getProfilePicture() + instagramm.getUsername());
                    getViewState().showPicture(instagramm.getProfilePicture());
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
