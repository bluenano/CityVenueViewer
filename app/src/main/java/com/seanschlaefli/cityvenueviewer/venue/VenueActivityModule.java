package com.seanschlaefli.cityvenueviewer.venue;

import com.seanschlaefli.cityvenueviewer.bookmark.BookmarkInteractor;
import com.seanschlaefli.cityvenueviewer.data.local.dao.CityDao;
import com.seanschlaefli.cityvenueviewer.data.local.dao.PhotoURLDao;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public abstract class VenueActivityModule {

    @Provides
    public static VenuePresenter provideVenuePresenter(VenueView view, PhotoURLDao photoURLDao,
                                                       CityDao cityDao, BookmarkInteractor bookmarkInteractor,
                                                       Retrofit retrofit) {
        return new VenuePresenter(view, photoURLDao, cityDao, bookmarkInteractor, retrofit);
    }

    @Binds
    abstract VenueView provideVenueView(VenueActivity venueActivity);
}
