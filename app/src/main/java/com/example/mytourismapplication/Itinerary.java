package com.example.mytourismapplication;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "itineraries")
public class Itinerary {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String locations;
    private String transportMethod;
    private boolean agreeRecommended;
    private String date;
    private int excitement;
    private String itineraryName;
    @ForeignKey
            (entity = User.class,
                    parentColumns = "id_user",
                    childColumns = "id_fkuser",
                    onDelete = CASCADE
            )
    private long id_fkuser;

    public long getId_fkuser() {
        return id_fkuser;
    }

    public void setId_fkuser(long id_fkuser) {
        this.id_fkuser = id_fkuser;
    }

    @Ignore
    public Itinerary(){}

    public Itinerary(String locations, String transportMethod, boolean agreeRecommended, String date, int excitement, String itineraryName) {
        this.locations = locations;
        this.transportMethod = transportMethod;
        this.agreeRecommended = agreeRecommended;
        this.date = date;
        this.excitement = excitement;
        this.itineraryName = itineraryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getTransportMethod() {
        return transportMethod;
    }

    public void setTransportMethod(String transportMethod) {
        this.transportMethod = transportMethod;
    }

    public boolean isAgreeRecommended() {
        return agreeRecommended;
    }

    public void setAgreeRecommended(boolean agreeRecommended) {
        this.agreeRecommended = agreeRecommended;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getExcitement() {
        return excitement;
    }

    public void setExcitement(int excitement) {
        this.excitement = excitement;
    }

    public String getItineraryName() {
        return itineraryName;
    }

    public void setItineraryName(String itineraryName) {
        this.itineraryName = itineraryName;
    }

    @Override
    public String toString() {
        return "Itinerary{" +
                "id=" + id +
                ", locations=" + locations +
                ", transportMethod='" + transportMethod + '\'' +
                ", agreeRecommended=" + agreeRecommended +
                ", date='" + date + '\'' +
                ", excitement=" + excitement +
                ", itineraryName='" + itineraryName + '\'' +
                '}';
    }
}
