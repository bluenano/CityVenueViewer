package com.seanschlaefli.cityvenueviewer.photo;

import com.seanschlaefli.cityvenueviewer.data.local.entity.BookmarkedVenue;
import com.seanschlaefli.cityvenueviewer.data.local.entity.PhotoURL;
import com.seanschlaefli.cityvenueviewer.bookmark.BookmarkInteractor;
import com.seanschlaefli.cityvenueviewer.data.local.dao.PhotoURLDao;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class PhotoPresenter {

    private PhotoView view;
    private PhotoURLDao photoURLDao;
    private BookmarkInteractor bookmarkInteractor;
    private List<PhotoURL> urls;

    @Inject
    public PhotoPresenter(PhotoView view,
                          PhotoURLDao photoURLDao,
                          BookmarkInteractor bookmarkInteractor) {
        this.view = view;
        this.photoURLDao = photoURLDao;
        this.bookmarkInteractor = bookmarkInteractor;
        urls = new ArrayList<>();
    }

    public void initializeBookmark(String venueId) {
        view.showBookmarkState(bookmarkInteractor.isBookmarked(venueId));
    }

    public void handleBookmarkClick(String venueId) {
        BookmarkedVenue bookmarked = bookmarkInteractor.getBookmarkedVenue(venueId);
        if (bookmarked != null) {
            bookmarkInteractor.delete(bookmarked);
            view.showBookmarkState(false);
        } else {
            bookmarkInteractor.insert(new BookmarkedVenue(venueId));
            view.showBookmarkState(true);
        }
    }

    public int getPhotoCount() {
        return urls.size();
    }

    public String getURL(int position) {
        return urls.get(position).getURL();
    }

    public void requestData(String venueId) {
        urls = photoURLDao.getPhotoURLsByVenue(venueId);
        if (urls.size() > 0) {
            view.setAdapter();
        } else {
            view.displayEmptyGrid();
        }
    }
}

