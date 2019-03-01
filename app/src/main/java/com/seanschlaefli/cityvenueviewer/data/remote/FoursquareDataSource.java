package com.seanschlaefli.cityvenueviewer.data.remote;

import androidx.annotation.NonNull;
import com.seanschlaefli.cityvenueviewer.BuildConfig;
import com.seanschlaefli.cityvenueviewer.data.local.City;
import com.seanschlaefli.cityvenueviewer.data.remote.model.venue.Venue;
import com.seanschlaefli.cityvenueviewer.data.remote.model.venue.VenuesByCity;
import com.seanschlaefli.cityvenueviewer.data.remote.model.venue.photo.PhotosByVenue;
import com.seanschlaefli.cityvenueviewer.venue.OnFoursquareFinishedListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.HashMap;
import java.util.Map;

public class FoursquareDataSource {

    private FoursquareService service;
    private OnFoursquareFinishedListener.OnVenuesFinishedListener venueListener;
    private OnFoursquareFinishedListener.OnPhotosFinishedListener photoListener;

    public FoursquareDataSource(FoursquareService service,
                                OnFoursquareFinishedListener.OnVenuesFinishedListener venueListener,
                                OnFoursquareFinishedListener.OnPhotosFinishedListener photoListener) {
        this.service = service;
        this.venueListener = venueListener;
        this.photoListener = photoListener;
    }

    public void fetchVenues(final City city) {
        Map<String, String> options = getRequiredOptionsMap();
        options.put(BuildConfig.VENUE_PARAMETER_NEAR, city.toString());
        options.put(BuildConfig.PARAMETER_LIMIT, BuildConfig.VENUE_LIMIT);
        Call<VenuesByCity> call = service.getVenuesByCity(options);
        call.enqueue(new Callback<VenuesByCity>() {
            @Override
            public void onResponse(@NonNull Call<VenuesByCity> call, @NonNull Response<VenuesByCity> response) {
                if (response.isSuccessful() && response.body() != null) {
                    venueListener.onVenuesLoadFinished(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<VenuesByCity> call, @NonNull Throwable t) {
                venueListener.onVenuesLoadFailure(t);
            }
        });
    }

    public void fetchPhotos(final Venue venue, int position) {
        Map<String, String> options = getRequiredOptionsMap();
        options.put(BuildConfig.PARAMETER_LIMIT, BuildConfig.PHOTO_LIMIT);
        Call<PhotosByVenue> call = service.getPhotosByVenue(venue.getId(), options);
        call.enqueue(new Callback<PhotosByVenue>() {
            @Override
            public void onResponse(@NonNull Call<PhotosByVenue> call, @NonNull Response<PhotosByVenue> response) {
                if (response.isSuccessful() && response.body() != null) {
                    photoListener.onPhotosLoadFinished(venue, response.body(), position);
                }
            }

            @Override
            public void onFailure(@NonNull Call<PhotosByVenue> call, @NonNull Throwable t) {
                photoListener.onPhotosLoadFailure(t);
            }
        });
    }

    private Map<String, String> getRequiredOptionsMap() {
        Map<String, String> options = new HashMap<>();
        options.put(BuildConfig.FOURSQUARE_PARAMETER_CLIENT, BuildConfig.FOURSQUARE_CLIENT_KEY);
        options.put(BuildConfig.FOURSQUARE_PARAMETER_SECRET, BuildConfig.FOURSQUARE_CLIENT_SECRET);
        options.put(BuildConfig.FOURSQUARE_PARAMETER_VERSIONING, BuildConfig.FOURSQUARE_VERSIONING);
        return options;
    }

}
