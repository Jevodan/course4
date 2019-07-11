package ru.geekbrains.gb_android_libraries.di;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.gb_android_libraries.mvp.model.api.INetworkStatus;
import ru.geekbrains.gb_android_libraries.ui.NetworkStatus;

@Module
public class UtilsModule {
    @Provides
    public INetworkStatus networkStatus() {
        return new NetworkStatus();
    }
}
