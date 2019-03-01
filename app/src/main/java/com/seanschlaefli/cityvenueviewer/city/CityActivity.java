package com.seanschlaefli.cityvenueviewer.city;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.seanschlaefli.cityvenueviewer.R;
import com.seanschlaefli.cityvenueviewer.venue.VenueActivity;
import dagger.android.AndroidInjection;

import javax.inject.Inject;

public class CityActivity extends AppCompatActivity implements CityView {

    private RecyclerView recyclerView;

    @Inject
    CityPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidInjection.inject(this);
        setContentView(R.layout.activity_city);
        initializeRecyclerView();
        presenter.requestData();
    }

    @Override
    public void startVenueActivity(long cityId) {
        Intent intent = VenueActivity.newIntent(cityId);
        startActivity(intent);
    }

    @Override
    public void setAdapter() {
        recyclerView.setAdapter(new CityRecyclerViewAdapter(presenter));
    }

    private void initializeRecyclerView() {
        recyclerView = findViewById(R.id.city_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CityActivity.this);
        ((LinearLayoutManager) layoutManager).setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        DividerItemDecoration horizontalLines = new DividerItemDecoration(recyclerView.getContext(),
                ((LinearLayoutManager) layoutManager).getOrientation());
        recyclerView.addItemDecoration(horizontalLines);
    }
}
