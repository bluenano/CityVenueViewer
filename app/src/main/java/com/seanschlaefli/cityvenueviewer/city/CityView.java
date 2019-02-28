package com.seanschlaefli.cityvenueviewer.city;

import com.seanschlaefli.cityvenueviewer.data.local.City;

public interface CityView {
    void startVenueActivity(City city);
    void setAdapter();
}