package com.seanschlaefli.cityvenueviewer.bookmark;

import com.seanschlaefli.cityvenueviewer.data.local.entity.BookmarkedVenue;
import com.seanschlaefli.cityvenueviewer.data.local.dao.BookmarkedVenueDao;

import javax.inject.Inject;

public class BookmarkInteractor {

    private BookmarkedVenueDao bookmarkedVenueDao;

    @Inject
    public BookmarkInteractor(BookmarkedVenueDao bookmarkedVenueDao) {
        this.bookmarkedVenueDao = bookmarkedVenueDao;
    }

    public boolean isBookmarked(String venueId) {
        return getBookmarkedVenue(venueId) != null;
    }

    public BookmarkedVenue getBookmarkedVenue(String venueId) {
        return bookmarkedVenueDao.loadByVenueId(venueId);
    }

    public void insert(BookmarkedVenue venue) {
        bookmarkedVenueDao.insert(venue);
    }

    public void delete(BookmarkedVenue venue) {
        bookmarkedVenueDao.delete(venue);
    }
}
