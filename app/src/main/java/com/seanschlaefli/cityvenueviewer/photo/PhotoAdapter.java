package com.seanschlaefli.cityvenueviewer.photo;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.seanschlaefli.cityvenueviewer.R;

public class PhotoAdapter extends BaseAdapter {

    private Context context;
    private PhotoPresenter presenter;

    public PhotoAdapter(Context context, PhotoPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public int getCount() {
        return presenter.getPhotoCount();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        String url = presenter.getURL(i);
        ImageView photo = new ImageView(context);
        Glide.with(context)
                .load(url)
                .override(100, 100)
                .centerCrop()
                .placeholder(R.mipmap.ic_no_image)
                .error(R.mipmap.ic_no_image)
                .into(photo);
        return photo;
    }
}
