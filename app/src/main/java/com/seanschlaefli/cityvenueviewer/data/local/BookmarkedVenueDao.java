package com.seanschlaefli.cityvenueviewer.data.local;

import androidx.room.*;

import java.util.List;

@Dao
public interface BookmarkedVenueDao {

    @Insert
    long insert(BookmarkedVenue venue);

    @Insert
    long[] insertAll(List<BookmarkedVenue> venues);

    @Delete
    void delete(BookmarkedVenue venue);

    @Update
    void update(BookmarkedVenue venue);

    @Query("SELECT * FROM BookmarkedVenue")
    List<BookmarkedVenue> getAll();

    @Query("SELECT * FROM BookmarkedVenue WHERE foursquare_venue_id= :foursquareVenueId")
    BookmarkedVenue loadByVenueId(String foursquareVenueId);
}
