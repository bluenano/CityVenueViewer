package com.seanschlaefli.cityvenueviewer.di;

import com.seanschlaefli.cityvenueviewer.city.CityActivity;
import com.seanschlaefli.cityvenueviewer.city.CityActivityModule;
import com.seanschlaefli.cityvenueviewer.photo.PhotoActivity;
import com.seanschlaefli.cityvenueviewer.photo.PhotoActivityModule;
import com.seanschlaefli.cityvenueviewer.venue.VenueActivity;
import com.seanschlaefli.cityvenueviewer.venue.VenueActivityModule;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = CityActivityModule.class)
    abstract CityActivity bindCityActivity();

    @ContributesAndroidInjector(modules = VenueActivityModule.class)
    abstract VenueActivity bindVenueActivity();

    @ContributesAndroidInjector(modules = PhotoActivityModule.class)
    abstract PhotoActivity bindPhotoActivity();

}
