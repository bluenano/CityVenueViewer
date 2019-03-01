package com.seanschlaefli.cityvenueviewer.photo;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.seanschlaefli.cityvenueviewer.App;
import com.seanschlaefli.cityvenueviewer.R;
import com.seanschlaefli.cityvenueviewer.venue.VenueActivity;
import dagger.android.AndroidInjection;

import javax.inject.Inject;

public class PhotoActivity extends AppCompatActivity implements PhotoView {

    public static String EXTRA_VENUE_ID = "venue_id";
    public static String EXTRA_VENUE_NAME = "venue_name";
    public static String EXTRA_VENUE_POSITION = "venue_position";

    private GridView grid;
    private ImageView bookmark;
    private TextView emptyGrid;
    private String venueId;
    private int venuePosition;

    @Inject
    PhotoPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_photo);

        grid = findViewById(R.id.photo_grid);
        TextView venueName = findViewById(R.id.photo_name_text);
        bookmark = findViewById(R.id.photo_bookmark_image);
        emptyGrid = findViewById(R.id.empty_grid_text);

        Intent intent = getIntent();
        String name = null;
        if (intent != null) {
            venuePosition = intent.getIntExtra(EXTRA_VENUE_POSITION, -1);
            venueId = intent.getStringExtra(EXTRA_VENUE_ID);
            name = intent.getStringExtra(EXTRA_VENUE_NAME);
        }

        if (venueId != null && name != null) {
            venueName.setText(name);
            presenter.initializeBookmark(venueId);
            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.handleBookmarkClick(venueId);
                }
            });
            presenter.requestData(venueId);
        } else {
            // show error, cannot continue without a venue id
            Toast.makeText(this,
                    "Fatal error",
                    Toast.LENGTH_LONG)
                    .show();
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = VenueActivity.newReturnIntent(venuePosition);
        setResult(RESULT_OK, intent);
        finish();
    }

    public static Intent newIntent(String venueId, String venueName, int venuePosition) {
        Intent intent = new Intent(App.getContext(), PhotoActivity.class);
        intent.putExtra(EXTRA_VENUE_ID, venueId);
        intent.putExtra(EXTRA_VENUE_NAME, venueName);
        intent.putExtra(EXTRA_VENUE_POSITION, venuePosition);
        return intent;
    }

    @Override
    public void showBookmarkState(boolean isBookmarked) {
        Resources r = App.getContext().getResources();
        Drawable image = isBookmarked ?
                r.getDrawable(R.mipmap.ic_bookmarked) :
                r.getDrawable(R.mipmap.ic_not_bookmarked);
        bookmark.setImageDrawable(image);
    }

    @Override
    public void setAdapter() {
        grid.setAdapter(new PhotoAdapter(this, presenter));
    }

    @Override
    public void displayEmptyGrid() {
        emptyGrid.setVisibility(View.VISIBLE);
    }
}
