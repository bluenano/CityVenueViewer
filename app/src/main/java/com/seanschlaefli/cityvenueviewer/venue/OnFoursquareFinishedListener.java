package com.seanschlaefli.cityvenueviewer.venue;

import com.seanschlaefli.cityvenueviewer.data.remote.model.venue.Venue;
import com.seanschlaefli.cityvenueviewer.data.remote.model.venue.VenuesByCity;
import com.seanschlaefli.cityvenueviewer.data.remote.model.venue.photo.PhotosByVenue;

public interface OnFoursquareFinishedListener {

    interface OnPhotosFinishedListener {
        void onPhotosLoadFinished(Venue venue, PhotosByVenue photos, int position);
        void onPhotosLoadFailure(Throwable t);
    }

    interface OnVenuesFinishedListener {
        void onVenuesLoadFinished(VenuesByCity venuesByCity);
        void onVenuesLoadFailure(Throwable t);
    }
}
