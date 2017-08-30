package io.napadlek.graphapi.business.service;

import io.napadlek.graphapi.business.data.LocationDO;

public class PlaceLocationDO {
    private String name;
    private double latitude;
    private double longitude;

    public PlaceLocationDO(String name, LocationDO location) {
        this.name = name;
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
