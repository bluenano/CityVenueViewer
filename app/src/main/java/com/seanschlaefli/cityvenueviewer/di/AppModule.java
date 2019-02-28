package com.seanschlaefli.cityvenueviewer.di;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
            return application;
    }
}
