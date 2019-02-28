package com.seanschlaefli.cityvenueviewer.venue;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.seanschlaefli.cityvenueviewer.App;
import com.seanschlaefli.cityvenueviewer.BuildConfig;
import com.seanschlaefli.cityvenueviewer.R;
import com.seanschlaefli.cityvenueviewer.data.local.City;
import dagger.android.AndroidInjection;

import javax.inject.Inject;

public class VenueActivity extends AppCompatActivity implements VenueView {

    private static final String EXTRA_CITY_ID = "city_id";
    private static final String EXTRA_VENUE_ID = "venue_id";
    private static final int PHOTO_REQUEST_CODE = 1;

    private RecyclerView recyclerView;
    private VenueRecyclerViewAdapter adapter;

    @Inject
    VenuePresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_venue);

        Intent intent = getIntent();
        long id = -1;
        if (intent != null) {
            id = intent.getLongExtra(EXTRA_CITY_ID, -1);
        }
        if (id != -1) {
            initializeRecyclerView();
            presenter.requestVenueData(id);
        } else {
            // handle error here, can't use this Activity without a city id
            Toast.makeText(this,
                    "Fatal Error",
                    Toast.LENGTH_LONG)
                    .show();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            presenter.clearPhotoURLs();
        }
    }

    private void initializeRecyclerView() {
        recyclerView = findViewById(R.id.venue_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(VenueActivity.this);
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration horizontalLines = new DividerItemDecoration(recyclerView.getContext(),
                ((LinearLayoutManager) layoutManager).getOrientation());
        recyclerView.addItemDecoration(horizontalLines);
    }

    public static Intent newIntent(City city) {
        Intent intent = new Intent(App.getContext(), VenueActivity.class);
        intent.putExtra(EXTRA_CITY_ID, city.getId());
        return intent;
    }

    public static Intent newReturnIntent(String venueId) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_VENUE_ID, venueId);
        return intent;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PHOTO_REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null) {
                String venueId = data.getStringExtra(EXTRA_VENUE_ID);
                presenter.updateVenueBookmark(venueId);
            }
        }
    }

    @Override
    public void startPhotoActivity(Intent intent) {
        startActivityForResult(intent, PHOTO_REQUEST_CODE);
    }

    @Override
    public void setAdapter() {
        adapter = new VenueRecyclerViewAdapter(presenter);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void showFailure() {
        Toast.makeText(this,
                "API call to " + BuildConfig.FOURSQUARE_BASE_URL + " failed",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void updateAdapter(int position) {
        adapter.notifyItemChanged(position);
    }

}
