package com.example.mytourismapplication;

import java.util.List;

public class City {
    private String name;
    private String country;
    private List<Place> places;

    public City(String name, String country, List<Place> places) {
        this.name = name;
        this.country = country;
        this.places = places;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public List<Place> getPlaces() {
        return places;
    }
}
