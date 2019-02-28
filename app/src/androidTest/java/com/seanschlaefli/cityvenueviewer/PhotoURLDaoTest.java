package com.seanschlaefli.cityvenueviewer;

import android.content.Context;

import android.util.Log;
import androidx.test.core.app.ApplicationProvider;
import com.seanschlaefli.cityvenueviewer.data.local.AppDatabase;
import com.seanschlaefli.cityvenueviewer.data.local.PhotoURL;
import com.seanschlaefli.cityvenueviewer.data.local.PhotoURLDao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import androidx.room.Room;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PhotoURLDaoTest {

    private static final String TAG = PhotoURLDaoTest.class.getSimpleName();
    private AppDatabase testDatabase;
    private PhotoURLDao dao;

    @Before
    public void getDao() {
        Context context = ApplicationProvider.getApplicationContext();
        testDatabase = Room.inMemoryDatabaseBuilder(
                context,
                AppDatabase.class
        ).build();
        dao = testDatabase.photoURLDao();
    }

    @After
    public void closeDb() {
        testDatabase.close();
    }

    @Test
    public void writeAndReadSinglePhotoURL() throws Exception {
        PhotoURL photoURL = new PhotoURL("some_venue_id", "https://some.url.com");
        dao.insert(photoURL);
        List<PhotoURL> items = dao.getPhotoURLsByVenue(photoURL.getVenueId());
        assertEquals(1, items.size());
        PhotoURL result = items.get(0);
        assertEquals(photoURL.getURL(), result.getURL());
        assertEquals(photoURL.getVenueId(), result.getVenueId());
    }


    @Test
    public void writeAndReadMultiplePhotoURL() throws Exception {
        String venueId = "some_venue_id";
        String url = "https://some.url.com";
        List<PhotoURL> photoURLs = Arrays.asList(
                new PhotoURL(venueId, url),
                new PhotoURL(venueId, url),
                new PhotoURL(venueId, url)
        );
        dao.insertAll(photoURLs);
        List<PhotoURL> items = dao.getPhotoURLsByVenue(venueId);
        assertEquals(3, items.size());
        PhotoURL result = items.get(0);
        assertEquals(venueId, result.getVenueId());
        assertEquals(url, result.getURL());
    }

    @Test
    public void deleteSinglePhotoURL() throws Exception {
        PhotoURL photoURL = new PhotoURL("some_venue_id", "https://some.url.com");
        photoURL.photoUrlId = dao.insert(photoURL);
        dao.delete(photoURL);
        List<PhotoURL> result = dao.getPhotoURLsByVenue(photoURL.getVenueId());
        assertEquals(0, result.size());
    }

    @Test
    public void updateSinglePhotoURL() throws Exception {
        PhotoURL photoURL = new PhotoURL("some_venue_id", "https://some.url.com");
        photoURL.photoUrlId = dao.insert(photoURL);
        Log.d(TAG, "photo URL id is " + photoURL.photoUrlId);
        photoURL.setPhotoURL("https://some.url.new.com");
        dao.update(photoURL);
        List<PhotoURL> items = dao.getPhotoURLsByVenue(photoURL.getVenueId());
        assertEquals(1, items.size());
        PhotoURL result = items.get(0);
        assertEquals(photoURL.getVenueId(), result.getVenueId());
        assertEquals(photoURL.getURL(), result.getURL());
    }
}
