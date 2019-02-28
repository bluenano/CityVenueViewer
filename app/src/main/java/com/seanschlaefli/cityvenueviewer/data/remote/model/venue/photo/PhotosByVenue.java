
package com.seanschlaefli.cityvenueviewer.data.remote.model.venue.photo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PhotosByVenue {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("response")
    @Expose
    private Response response;

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<String> getPhotoUrls() {
        List<String> urls = new ArrayList<>();
        for (Item item: response.getPhotos().getItems()) {
            urls.add(item.getPhotoUrl());
        }
        return urls;
    }

}
