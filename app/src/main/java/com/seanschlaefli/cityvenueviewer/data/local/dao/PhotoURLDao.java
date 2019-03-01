package com.seanschlaefli.cityvenueviewer.data.local.dao;

import androidx.room.*;
import com.seanschlaefli.cityvenueviewer.data.local.entity.PhotoURL;

import java.util.List;

@Dao
public interface PhotoURLDao {

    @Insert
    long insert(PhotoURL photoURL);

    @Insert
    long[] insertAll(List<PhotoURL> urls);

    @Delete
    void delete(PhotoURL photoURL);

    @Update
    void update(PhotoURL photoURL);

    @Query("SELECT * FROM photourl WHERE venue_id = :venueId")
    List<PhotoURL> getPhotoURLsByVenue(String venueId);

    @Query("DELETE FROM photourl")
    void clearAll();
}
