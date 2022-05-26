package com.example.mytourismapplication;

public class Transport {

    private String station;
    private String transportType;

    public Transport(String station, String transportType) {
        this.station = station;
        this.transportType = transportType;
    }

    public String getStation() {
        return station;
    }

    public String getTransportType() {
        return transportType;
    }

    @Override
    public String toString() {
        return "Transport: " +
                "station='" + station + '\'' +
                ", transportType='" + transportType;
    }
}
