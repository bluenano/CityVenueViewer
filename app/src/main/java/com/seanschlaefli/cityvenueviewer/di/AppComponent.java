package com.seanschlaefli.cityvenueviewer.di;

import android.app.Application;
import com.seanschlaefli.cityvenueviewer.App;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

import javax.inject.Singleton;

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        DatabaseModule.class,
        NetworkModule.class,
        ActivityBuilder.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        Builder database(DatabaseModule database);
        Builder network(NetworkModule network);
        AppComponent build();
    }

    void inject(App app);
}
