package com.seanschlaefli.cityvenueviewer.data.remote;

import com.seanschlaefli.cityvenueviewer.data.remote.model.venue.VenuesByCity;
import com.seanschlaefli.cityvenueviewer.data.remote.model.venue.photo.PhotosByVenue;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import java.util.Map;

public interface FoursquareService {

    @GET("/v2/venues/search")
    Call<VenuesByCity> getVenuesByCity(
            @QueryMap Map<String, String> options
    );

    @GET("/v2/venues/{venue_id}/photos")
    Call<PhotosByVenue> getPhotosByVenue(
            @Path("venue_id") String venueId,
            @QueryMap Map<String, String> options
    );
}
