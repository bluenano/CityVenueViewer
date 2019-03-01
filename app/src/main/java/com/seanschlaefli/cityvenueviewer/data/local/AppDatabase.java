package com.seanschlaefli.cityvenueviewer.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.seanschlaefli.cityvenueviewer.data.local.dao.BookmarkedVenueDao;
import com.seanschlaefli.cityvenueviewer.data.local.dao.CityDao;
import com.seanschlaefli.cityvenueviewer.data.local.dao.PhotoURLDao;
import com.seanschlaefli.cityvenueviewer.data.local.entity.BookmarkedVenue;
import com.seanschlaefli.cityvenueviewer.data.local.entity.City;
import com.seanschlaefli.cityvenueviewer.data.local.entity.PhotoURL;

@Database(entities = {City.class,
        BookmarkedVenue.class,
        PhotoURL.class},
          version = 1
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CityDao cityDao();
    public abstract BookmarkedVenueDao bookmarkedVenueDao();
    public abstract PhotoURLDao photoURLDao();
}
