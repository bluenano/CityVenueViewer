package com.seanschlaefli.cityvenueviewer;

import com.seanschlaefli.cityvenueviewer.city.CityPresenter;
import com.seanschlaefli.cityvenueviewer.city.CityView;
import com.seanschlaefli.cityvenueviewer.data.local.City;
import com.seanschlaefli.cityvenueviewer.data.local.CityDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;

@RunWith(RobolectricTestRunner.class)
@Config(manifest = "src/main/AndroidManifest.xml")
public class CityPresenterTest {

    @Mock
    private CityView view;

    @Mock
    private CityDao cityDao;

    @Mock
    private List<City> cities;

    private CityPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new CityPresenter(view, cityDao);
        presenter.setCities(cities);
    }

    @Test
    public void requestDataTest() {
        presenter.requestData();
        Mockito.verify(view).setAdapter();
    }

    @Test
    public void handleCityClickTest() {
        presenter.handleCityClick(0);
        Mockito.verify(view).startVenueActivity(cities.get(0));
    }
}
