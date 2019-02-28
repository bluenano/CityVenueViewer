package com.seanschlaefli.cityvenueviewer.venue;

import android.content.Intent;

public interface VenueView {
    void startPhotoActivity(Intent intent);
    void setAdapter();
    void showFailure();
    void updateAdapter(int position);
}
