package com.seanschlaefli.cityvenueviewer.venue;

import com.seanschlaefli.cityvenueviewer.data.remote.model.venue.Venue;
import com.seanschlaefli.cityvenueviewer.data.remote.model.venue.VenuesByCity;
import com.seanschlaefli.cityvenueviewer.data.remote.model.venue.photo.PhotosByVenue;

public interface OnFoursquareFinishedListener {

    interface OnVenuesFinishedListener {
        void onVenuesLoadFinished(VenuesByCity venuesByCity);
        void onVenuesLoadFailure(Throwable t);
    }

    interface OnPhotosFinishedListener {
        void onPhotosLoadFinished(Venue venue, int position, PhotosByVenue photos);
        void onPhotosLoadFailure(Throwable t);
    }
}
