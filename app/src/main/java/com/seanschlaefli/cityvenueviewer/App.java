package com.seanschlaefli.cityvenueviewer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import com.seanschlaefli.cityvenueviewer.di.DaggerAppComponent;
import com.seanschlaefli.cityvenueviewer.di.DatabaseModule;
import com.seanschlaefli.cityvenueviewer.di.NetworkModule;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

import javax.inject.Inject;

public class App extends Application implements HasActivityInjector {

    private static App INSTANCE;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        DaggerAppComponent
                .builder()
                .application(this)
                .database(new DatabaseModule())
                .network(new NetworkModule())
                .build()
                .inject(this);
    }

    public static Context getContext(){
        return INSTANCE.getApplicationContext();
    }

    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}