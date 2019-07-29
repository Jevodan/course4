package ru.geekbrains.gb_android_libraries.di;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.gb_android_libraries.mvp.model.api.IDataSource;
import ru.geekbrains.gb_android_libraries.mvp.model.api.INetworkStatus;
import ru.geekbrains.gb_android_libraries.mvp.model.cache.ICache;
import ru.geekbrains.gb_android_libraries.mvp.model.repo.IUsersRepo;
import ru.geekbrains.gb_android_libraries.mvp.model.repo.UsersRepo;

import javax.inject.Named;

@Module(includes = {CacheModule.class, ApiModule.class})
public class RepoModule {

    @Provides
    public IUsersRepo usersRepo(INetworkStatus networkStatus, IDataSource dataSource, @Named("room") ICache cache) {
        return new UsersRepo(networkStatus, dataSource, cache);
    }
}
