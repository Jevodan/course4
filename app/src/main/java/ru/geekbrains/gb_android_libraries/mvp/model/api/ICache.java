package ru.geekbrains.gb_android_libraries.mvp.model.api;

import java.util.List;

import io.reactivex.Single;
import ru.geekbrains.gb_android_libraries.mvp.model.entity.Repository;
import ru.geekbrains.gb_android_libraries.mvp.model.entity.User;

public interface ICache {

    User putUser(String username, User user);
    Single<User> getUser(String username);
    List<Repository> putRepo(User user, List<Repository> repos);
    Single<List<Repository>> getRepo(User user);

}
