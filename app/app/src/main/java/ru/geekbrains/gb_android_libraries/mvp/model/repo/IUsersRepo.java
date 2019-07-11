package ru.geekbrains.gb_android_libraries.mvp.model.repo;

import io.reactivex.Single;
import ru.geekbrains.gb_android_libraries.mvp.model.entity.Repository;
import ru.geekbrains.gb_android_libraries.mvp.model.entity.User;

import java.util.List;

public interface IUsersRepo {

    Single<User> getUser(String username);
    Single<List<Repository>> getUserRepos(User user);
}
