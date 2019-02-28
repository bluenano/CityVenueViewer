package com.seanschlaefli.cityvenueviewer.data.local;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {City.class,
        BookmarkedVenue.class,
        PhotoURL.class},
          version = 1
)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CityDao cityDao();
    public abstract BookmarkedVenueDao bookmarkedVenueDao();
    public abstract PhotoURLDao photoURLDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        synchronized (AppDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = buildDatabase(context);
            }
        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                "cityvenueviewer.db")
                .fallbackToDestructiveMigration()
                // Room will throw an exception if you run queries on the main thread without this
                // typically I write AsyncTask implementations and pass callback interfaces
                // to implement queries off the main thread
                .allowMainThreadQueries()
                .build();
    }
}
