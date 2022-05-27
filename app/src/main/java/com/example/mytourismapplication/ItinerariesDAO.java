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

    @Query("SELECT * FROM itineraries WHERE transportMethod = :transport")
    List<Itinerary> searchByTransport(String transport);

    @Query("SELECT * FROM itineraries WHERE locations IN (:locationsList)")
    List<Itinerary> getAllWithPairs(String locationsList);

    @Query("UPDATE itineraries SET itineraryName = :newName, excitement= :givenExcitement WHERE itineraryName =:existingName")
    void update(String newName, int givenExcitement, String existingName);

    @Delete
    void delete(Itinerary itinerary);
}
