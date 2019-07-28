package com.example.jevodan.instagram.di;


import com.example.jevodan.instagram.mvp.model.repo.IDataRepo;

import org.mockito.Mockito;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class TestRepoModule {

    @Singleton
    @Provides
    public IDataRepo dataRepo() {
        return Mockito.mock(IDataRepo.class);
    }

}
