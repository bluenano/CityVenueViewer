package com.seanschlaefli.cityvenueviewer.city;

import com.seanschlaefli.cityvenueviewer.data.local.entity.City;
import com.seanschlaefli.cityvenueviewer.data.local.dao.CityDao;

import javax.inject.Inject;
import java.util.List;

public class CityPresenter {

    private CityView cityView;
    private CityDao cityDao;
    private List<City> cities;

    @Inject
    public CityPresenter(CityView cityView, CityDao cityDao) {
        this.cityView = cityView;
        this.cityDao = cityDao;
    }

    public void onBindCityRowViewAtPosition(CityRowView rowView, int position) {
        City city = cities.get(position);
        rowView.setCityStateName(city.getName(), city.getState());
    }

    public int getCityRowsCount() {
        return cities.size();
    }

    public void requestData() {
        setCities(cityDao.getAll());
        if (cities.size() == 0) {
            cityDao.insertAll(City.populateData());
            cities = cityDao.getAll();
        }
        cityView.setAdapter();
    }

    public void handleCityClick(int position) {
        City city = cities.get(position);
        cityView.startVenueActivity(city.getId());
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
