package com.seanschlaefli.cityvenueviewer.venue;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.seanschlaefli.cityvenueviewer.App;
import com.seanschlaefli.cityvenueviewer.R;

public class VenueRecyclerViewAdapter extends RecyclerView.Adapter<VenueViewHolder>{

    private VenuePresenter venuePresenter;

    public VenueRecyclerViewAdapter(VenuePresenter venuePresenter) {
        this.venuePresenter = venuePresenter;
    }

    @NonNull
    @Override
    public VenueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(App.getContext())
                .inflate(R.layout.list_item_venue, parent, false);
        final VenueViewHolder holder = new VenueViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = holder.getAdapterPosition();
                if (itemPosition != RecyclerView.NO_POSITION) {
                    venuePresenter.handleVenueClick(itemPosition);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull VenueViewHolder holder, int position) {
        venuePresenter.onBindVenueRowViewAtPosition(holder, position);
    }

    @Override
    public int getItemCount() {
        return venuePresenter.getVenueRowsCount();
    }

}
