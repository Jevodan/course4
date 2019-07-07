package ru.geekbrains.gb_android_libraries.mvp.model.repo;

import java.util.List;

import io.paperdb.Paper;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import ru.geekbrains.gb_android_libraries.mvp.model.api.ApiHolder;
import ru.geekbrains.gb_android_libraries.mvp.model.api.ICache;
import ru.geekbrains.gb_android_libraries.mvp.model.api.INetworkStatus;
import ru.geekbrains.gb_android_libraries.mvp.model.entity.Repository;
import ru.geekbrains.gb_android_libraries.mvp.model.entity.User;
import ru.geekbrains.gb_android_libraries.ui.NetworkStatus;

public class MainUsersRepo {

    INetworkStatus networkStatus = new NetworkStatus();
    ICache inCache;

    public MainUsersRepo(ICache inCache) {
        this.inCache = inCache;
    }

    public Single<User> getUser(String username) {
        if(networkStatus.isOnline()){
            return ApiHolder.getApi().getUser(username)
                    .subscribeOn(Schedulers.io())
                    .map(user -> inCache.putUser(username, user));
        } else {
            return inCache.getUser(username);
        }
    }

    public Single<List<Repository>> getUserRepos(User user) {
        if(networkStatus.isOnline()){
            return ApiHolder.getApi().getUserRepos(user.getReposUrl())
                    .subscribeOn(Schedulers.io())
                    .map(repos -> inCache.putRepo(user, repos));
        } else {
            return inCache.getRepo(user);
        }
    }

}
