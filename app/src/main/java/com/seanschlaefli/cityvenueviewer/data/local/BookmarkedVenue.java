package com.seanschlaefli.cityvenueviewer.data.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BookmarkedVenue {

    public BookmarkedVenue(String foursquareVenueId) {
        this.foursquareVenueId = foursquareVenueId;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "venue_id")
    public long venueId;

    @ColumnInfo(name = "foursquare_venue_id")
    public String foursquareVenueId;

}
