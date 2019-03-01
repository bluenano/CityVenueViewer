package com.seanschlaefli.cityvenueviewer.di;

import android.app.Application;
import androidx.room.Room;
import com.seanschlaefli.cityvenueviewer.bookmark.BookmarkInteractor;
import com.seanschlaefli.cityvenueviewer.data.local.AppDatabase;
import com.seanschlaefli.cityvenueviewer.data.local.dao.BookmarkedVenueDao;
import com.seanschlaefli.cityvenueviewer.data.local.dao.CityDao;
import com.seanschlaefli.cityvenueviewer.data.local.dao.PhotoURLDao;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    AppDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                AppDatabase.class,
                "cityvenueviewer.db")
                .fallbackToDestructiveMigration()
                // Room will throw an exception if you run queries on the main thread without this
                // typically I write AsyncTask implementations and pass callback interfaces
                // to implement queries off the main thread
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public CityDao provideCityDao(AppDatabase db) {
        return db.cityDao();
    }

    @Provides
    @Singleton
    public BookmarkedVenueDao provideBookmarkedVenueDao(AppDatabase db) {
        return db.bookmarkedVenueDao();
    }

    @Provides
    @Singleton
    public PhotoURLDao providePhotoURLDao(AppDatabase db) {
        return db.photoURLDao();
    }

    @Provides
    @Singleton
    public BookmarkInteractor provideBookmarkInteractor(AppDatabase db) {
        return new BookmarkInteractor(db.bookmarkedVenueDao());
    }
}
