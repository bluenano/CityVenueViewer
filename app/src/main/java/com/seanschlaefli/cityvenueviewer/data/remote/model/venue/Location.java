
package com.seanschlaefli.cityvenueviewer.data.remote.model.venue;

import java.util.List;

import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Location {

    private final String UNAVAILABLE = "N/A";

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("crossStreet")
    @Expose
    private String crossStreet;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lng")
    @Expose
    private Double lng;
    @SerializedName("labeledLatLngs")
    @Expose
    private List<LabeledLatLng> labeledLatLngs = null;
    @SerializedName("distance")
    @Expose
    private Integer distance;
    @SerializedName("postalCode")
    @Expose
    private String postalCode;
    @SerializedName("cc")
    @Expose
    private String cc;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("formattedAddress")
    @Expose
    private List<String> formattedAddress = null;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCrossStreet() {
        return crossStreet;
    }

    public void setCrossStreet(String crossStreet) {
        this.crossStreet = crossStreet;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public List<LabeledLatLng> getLabeledLatLngs() {
        return labeledLatLngs;
    }

    public void setLabeledLatLngs(List<LabeledLatLng> labeledLatLngs) {
        this.labeledLatLngs = labeledLatLngs;
    }

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<String> getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(List<String> formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    @NonNull
    @Override
    public String toString() {
        // properties can be null so check them
        return Location.class.getSimpleName() + "\n" +
                "Address: " + getAddressString() + "\n" +
                "Cross Street: " + getCrossStreetString() + "\n" +
                "Latitude: " + getLatString() + "\n" +
                "Longitude: " + getLngString() + "\n" +
                "Distance: " + getDistanceString() + "\n" +
                "Postal Code: " + getPostalCodeString() + "\n" +
                "Country Code: " + getCountryCodeString() + "\n" +
                "City: " + getCityString() + "\n" +
                "State: " + getStateString() + "\n" +
                "Country: " + getCountryString();
    }

    public String getAddressString() {
        return address != null ? address : UNAVAILABLE;
    }

    public String getCrossStreetString() {
        return crossStreet != null ? crossStreet : UNAVAILABLE;
    }

    public String getLatString() {
        return lat != null ? Double.toString(lat) : UNAVAILABLE;
    }

    public String getLngString() {
        return lng != null ? Double.toString(lng) : UNAVAILABLE;
    }

    public String getDistanceString() {
        return distance != null ? Integer.toString(distance) : UNAVAILABLE;
    }

    public String getPostalCodeString() {
        return postalCode != null ? postalCode : UNAVAILABLE;
    }

    public String getCountryCodeString() {
        return cc != null ? cc : UNAVAILABLE;
    }

    public String getCityString() {
        return city != null ? city : UNAVAILABLE;
    }

    public String getStateString() {
        return state != null ? state : UNAVAILABLE;
    }

    public String getCountryString() {
        return country != null ? country : UNAVAILABLE;
    }
}
