package com.example.jevodan.instagram.di;


import com.example.jevodan.instagram.mvp.model.api.ApiData;
import com.example.jevodan.instagram.mvp.model.cache.IDataCache;
import com.example.jevodan.instagram.mvp.model.repo.DataRepo;
import com.example.jevodan.instagram.mvp.model.repo.IDataRepo;
import com.example.jevodan.instagram.ui.net.INetworkStatus;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module(includes = {CacheModule.class, ApiModule.class, NetModule.class})
public class RepoModule {

    @Provides
    public IDataRepo dataRepo(INetworkStatus networkStatus, ApiData apiData, @Named("room") IDataCache dataCache){
        return new DataRepo(networkStatus, apiData, dataCache);
    }

}
