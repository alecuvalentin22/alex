package com.example.mytourismapplication;

import java.util.List;

public class Place {
    private String name;
    private List<Transport> transportList;

    public Place(String name, List<Transport> transportList) {
        this.name = name;
        this.transportList = transportList;
    }

    public String getName() {
        return name;
    }

    public List<Transport> getTransportList() {
        return transportList;
    }

    @Override
    public String toString() {
        return "\nPlace: " +
                "name='" + name + '\'' +
                ", transportList=" + transportList + '\n';
    }
}
