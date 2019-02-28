package com.seanschlaefli.cityvenueviewer.city;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.seanschlaefli.cityvenueviewer.R;

public class CityViewHolder extends RecyclerView.ViewHolder implements CityRowView {

    private TextView cityStateTextView;

    public CityViewHolder(@NonNull View itemView) {
        super(itemView);
        cityStateTextView = itemView.findViewById(R.id.city_state_text);
    }

    @Override
    public void setCityStateName(String city, String state) {
        String result = cityStateTextView.getContext().getResources()
                .getString(R.string.city_state, city, state);
        cityStateTextView.setText(result);
    }
}