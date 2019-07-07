package ru.geekbrains.gb_android_libraries.ui;

import java.util.List;

import io.paperdb.Paper;
import io.reactivex.Single;
import ru.geekbrains.gb_android_libraries.mvp.model.api.ICache;
import ru.geekbrains.gb_android_libraries.mvp.model.entity.Repository;
import ru.geekbrains.gb_android_libraries.mvp.model.entity.User;

public class PaperImpl implements ICache {
    @Override
    public User putUser(String username, User user) {
        Paper.book("users").write(username, user);
        return user;
    }

    @Override
    public Single<User> getUser(String username) {
        if (!Paper.book("users").contains(username)) {
            return Single.error(new RuntimeException("No such user in cache"));
        }
        return Single.fromCallable(() -> Paper.book("users").read(username));
    }

    @Override
    public List<Repository> putRepo(User user, List<Repository> repos) {
        Paper.book("repos").write(user.getLogin(), repos);
        return repos;
    }

    @Override
    public Single<List<Repository>> getRepo(User user) {
        if (!Paper.book("repos").contains(user.getLogin())) {
            return Single.error(new RuntimeException("No such user in cache"));
        }
        return Single.fromCallable(() -> Paper.book("repos").read(user.getLogin()));
    }

}
