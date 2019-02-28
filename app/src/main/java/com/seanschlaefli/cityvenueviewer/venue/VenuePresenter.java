package com.seanschlaefli.cityvenueviewer.venue;

import com.seanschlaefli.cityvenueviewer.data.local.*;
import com.seanschlaefli.cityvenueviewer.data.remote.FoursquareDataSource;
import com.seanschlaefli.cityvenueviewer.data.remote.FoursquareService;
import com.seanschlaefli.cityvenueviewer.data.remote.model.venue.Venue;
import com.seanschlaefli.cityvenueviewer.data.remote.model.venue.VenuesByCity;
import com.seanschlaefli.cityvenueviewer.data.remote.model.venue.photo.PhotosByVenue;
import com.seanschlaefli.cityvenueviewer.photo.PhotoActivity;
import com.seanschlaefli.cityvenueviewer.bookmark.BookmarkInteractor;
import retrofit2.Retrofit;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class VenuePresenter implements OnFoursquareFinishedListener,
            OnFoursquareFinishedListener.OnVenuesFinishedListener,
            OnFoursquareFinishedListener.OnPhotosFinishedListener {

    private VenueView view;
    private CityDao cityDao;
    private PhotoURLDao photoURLDao;
    private BookmarkInteractor bookmarkInteractor;
    private FoursquareDataSource dataSource;
    private List<Venue> venues;

    @Inject
    public VenuePresenter(VenueView view, PhotoURLDao photoURLDao,
                          CityDao cityDao, BookmarkInteractor bookmarkInteractor,
                          Retrofit retrofit) {
        this.view = view;
        this.cityDao = cityDao;
        this.photoURLDao = photoURLDao;
        this.bookmarkInteractor = bookmarkInteractor;
        dataSource = new FoursquareDataSource(
                retrofit.create(FoursquareService.class),
                this,
                this
        );
        venues = new ArrayList<>();
    }

    public void requestVenueData(long cityId) {
        City city = cityDao.loadById(cityId);
        clearPhotoURLs();
        dataSource.fetchVenues(city);
    }

    private void requestPhotoData(Venue venue) {
        dataSource.fetchPhotos(venue);
    }

    private void requestVenuesPhotoData(List<Venue> venues) {
        for (Venue venue: venues) {
            requestPhotoData(venue);
        }
    }

    public int getVenueRowsCount() {
        return venues.size();
    }

    public void onBindVenueRowViewAtPosition(VenueRowView rowView, int position) {
        Venue venue = venues.get(position);
        rowView.setVenueName(venue.toString());
        rowView.setBookmarkIcon(venue.isBookmarked());
        rowView.setLocationInfo(venue.getLocationString());
        rowView.setCategoryInfo(venue.getCategoriesString());
        rowView.setVenueThumbnail(venue.getThumbnailUrl());
    }

    public void handleVenueClick(int position) {
        Venue venue = venues.get(position);
        view.startPhotoActivity(PhotoActivity.newIntent(venue.getId(), venue.getName()));
    }

    public void updateVenueBookmark(String venueId) {
        int position = findVenuePosition(venueId);
        if (position != -1) {
            Venue venue = venues.get(position);
            if (venue != null) {
                BookmarkedVenue bookmarked = bookmarkInteractor.getBookmarkedVenue(venueId);
                venue.setBookmarked(bookmarked != null);
                view.updateAdapter(position);
            }
        }

    }

    public void clearPhotoURLs() {
        photoURLDao.clearAll();
    }

    private void savePhotoURLs(String venueId, PhotosByVenue photos) {
        List<PhotoURL> urls = new ArrayList<>();
        for (String url: photos.getPhotoUrls()) {
            urls.add(new PhotoURL(venueId, url));
        }
        photoURLDao.insertAll(urls);
    }

    private void setBookmarkedVenues() {
        for (Venue venue: venues) {
            String id = venue.getId();
            if (bookmarkInteractor.isBookmarked(id)) {
                venue.setBookmarked(true);
            } else {
                venue.setBookmarked(false);
            }
        }
    }

    private int findVenuePosition(String venueId) {
        int position = -1;
        for (int i = 0; i < venues.size(); i++) {
            Venue current = venues.get(i);
            if (venueId.equals(current.getId())) {
                return i;
            }
        }
        return position;
    }

    @Override
    public void onVenuesLoadFinished(VenuesByCity venuesByCity) {
        if (venuesByCity != null) {
            venues = venuesByCity.getResponse().getVenues();
            setBookmarkedVenues();
            requestVenuesPhotoData(venues);
            view.setAdapter();
        }
    }

    @Override
    public void onVenuesLoadFailure(Throwable t) {
        view.showFailure();
    }

    @Override
    public void onPhotosLoadFinished(Venue venue, PhotosByVenue photos) {
        List<String> photoUrls = photos.getPhotoUrls();
        if (photoUrls.size() > 0) {
            venue.setThumbnailUrl(photoUrls.get(0));
            int position = findVenuePosition(venue.getId());
            view.updateAdapter(position);
            savePhotoURLs(venue.getId(), photos);
        }
    }

    @Override
    public void onPhotosLoadFailure(Throwable t) {
        view.showFailure();
    }
}
