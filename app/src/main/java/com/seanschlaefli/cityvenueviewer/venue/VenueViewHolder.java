package com.seanschlaefli.cityvenueviewer.venue;

import android.content.res.Resources;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.seanschlaefli.cityvenueviewer.App;
import com.seanschlaefli.cityvenueviewer.BuildConfig;
import com.seanschlaefli.cityvenueviewer.R;

public class VenueViewHolder extends RecyclerView.ViewHolder implements VenueRowView {

    private TextView name;
    private TextView location;
    private TextView categories;
    private ImageView thumbnail;
    private ImageView bookmark;

    public VenueViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.venue_name_text);
        location = itemView.findViewById(R.id.venue_location_text);
        categories = itemView.findViewById(R.id.venue_categories_text);
        thumbnail = itemView.findViewById(R.id.venue_thumbnail_image);
        bookmark = itemView.findViewById(R.id.venue_bookmark_image);
    }

    @Override
    public void setVenueName(String venueName) {
        name.setText(venueName);
    }

    @Override
    public void setVenueThumbnail(String url) {
        int scale = (int) App.getContext().getResources().getDisplayMetrics().density;
        int pixels = BuildConfig.BASE_THUMBNAIL_SIZE * scale;
        Glide.with(App.getContext())
                .load(url)
                .override(pixels, pixels)
                .centerCrop()
                .placeholder(R.mipmap.ic_no_image)
                .error(R.mipmap.ic_no_image)
                .into(thumbnail);
        thumbnail.setVisibility(View.VISIBLE);
    }

    @Override
    public void setLocationInfo(String locationInfo) {
        location.setText(locationInfo);
    }

    @Override
    public void setCategoryInfo(String categoryInfo) {
        categories.setText(categoryInfo);
    }

    @Override
    public void setBookmarkIcon(boolean isBookmarked) {
        Resources r = App.getContext().getResources();
        if (isBookmarked) {
            bookmark.setImageDrawable(r.getDrawable(R.mipmap.ic_bookmarked));
        } else {
            bookmark.setImageDrawable(r.getDrawable(R.mipmap.ic_not_bookmarked));
        }
    }
}
