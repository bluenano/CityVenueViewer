package com.seanschlaefli.cityvenueviewer.data.local;

import androidx.room.*;

import java.util.List;

@Dao
public interface CityDao {

    @Insert
    long insert(City city);

    @Insert
    long[] insertAll(City... cities);

    @Delete
    void delete(City city);

    @Update
    void update(City city);

    @Query("SELECT * FROM City")
    List<City> getAll();

    @Query("SELECT * FROM City WHERE city_id = :cityId")
    City loadById(long cityId);
}
