package com.example.jevodan.instagram.di;


import com.example.jevodan.instagram.ui.net.INetworkStatus;
import com.example.jevodan.instagram.ui.net.NetworkStatus;

import dagger.Module;
import dagger.Provides;

@Module
public class NetModule {

    @Provides
    public INetworkStatus networkStatus(){
        return new NetworkStatus();
    }
}
