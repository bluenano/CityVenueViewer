package com.seanschlaefli.cityvenueviewer.city;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.seanschlaefli.cityvenueviewer.R;

public class CityRecyclerViewAdapter extends RecyclerView.Adapter<CityViewHolder> {

    private CityPresenter cityPresenter;

    public CityRecyclerViewAdapter(CityPresenter cityPresenter) {
        this.cityPresenter = cityPresenter;
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_city, parent, false);
        final CityViewHolder holder = new CityViewHolder(v);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int itemPosition = holder.getAdapterPosition();
                if (itemPosition != RecyclerView.NO_POSITION) {
                    cityPresenter.handleCityClick(itemPosition);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        cityPresenter.onBindCityRowViewAtPosition(holder, position);
    }

    @Override
    public int getItemCount() {
        return cityPresenter.getCityRowsCount();
    }
}
