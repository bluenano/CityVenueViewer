package com.seanschlaefli.cityvenueviewer.data.local.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class City {

    public City(String name, String state) {
        this.name = name;
        this.state = state;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "city_id")
    public long cityId;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "state")
    public String state;

    @Ignore
    public static City[] populateData() {
        return new City[] {
                new City("San Francisco", "CA"),
                new City("Los Angeles", "CA"),
                new City("Seattle", "WA"),
                new City("New York City", "NY"),
                new City("Dallas", "TX")
        };
    }

    @Ignore
    public long getId() {
        return cityId;
    }

    @Ignore
    public String getName() {
        return name;
    }

    @Ignore
    public String getState() {
        return state;
    }

    @Ignore
    @Override
    public String toString() {
        return name + ", " + state;
    }
}
