package com.seanschlaefli.cityvenueviewer.city;

import com.seanschlaefli.cityvenueviewer.data.local.CityDao;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class CityActivityModule {

    @Provides
    public static CityPresenter provideCityPresenter(CityView cityView, CityDao cityDao) {
        return new CityPresenter(cityView, cityDao);
    }

    @Binds
    abstract CityView provideCityView(CityActivity cityActivity);
}
