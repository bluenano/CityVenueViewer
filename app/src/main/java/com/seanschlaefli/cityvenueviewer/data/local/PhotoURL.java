package com.seanschlaefli.cityvenueviewer.data.local;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class PhotoURL {

    public PhotoURL(String venueId, String photoURL) {
        this.venueId = venueId;
        this.photoURL = photoURL;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "photo_url_id")
    public long photoUrlId;

    @ColumnInfo(name = "venue_id")
    public String venueId;

    @ColumnInfo(name = "photo_url")
    public String photoURL;

    @Ignore
    public String getVenueId() {
        return venueId;
    }

    @Ignore
    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    @Ignore
    public String getURL() {
        return photoURL;
    }

    @Ignore
    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }
}
