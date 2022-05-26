package com.example.mytourismapplication;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ItinerariesDAO {

    @Insert
    long insert(Itinerary itinerary);

    @Query("select * from itineraries")
    List<Itinerary> getAll();

    @Query("delete from itineraries")
    void deleteAll();

    @Delete
    void delete(Itinerary itinerary);
}
