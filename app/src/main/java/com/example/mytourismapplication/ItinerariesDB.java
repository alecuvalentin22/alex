package com.example.mytourismapplication;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Itinerary.class, User.class}, version = 2, exportSchema = false)
public abstract class ItinerariesDB extends RoomDatabase {
    public static final String DB_NAME = "itineraries.db";
    private static ItinerariesDB instance;

    public static ItinerariesDB getInstance(Context context)
    {
        if(instance==null)
            instance = Room.databaseBuilder(context, ItinerariesDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        return instance;
    }

    public abstract ItinerariesDAO getItinerariesDAO();
    public abstract UsersDAO getUsersDAO();
}
