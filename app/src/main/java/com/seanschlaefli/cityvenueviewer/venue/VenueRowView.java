package com.seanschlaefli.cityvenueviewer.venue;

public interface VenueRowView {
    void setVenueName(String name);
    void setVenueThumbnail(String url);
    void setLocationInfo(String location);
    void setCategoryInfo(String categories);
    void setBookmarkIcon(boolean isBookmarked);
}
