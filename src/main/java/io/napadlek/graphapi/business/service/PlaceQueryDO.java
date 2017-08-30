package io.napadlek.graphapi.business.service;

public class PlaceQueryDO {
    private final String country;
    private final String city;
    private final String name;

    public PlaceQueryDO(String country, String city, String name) {
        this.country = country;
        this.city = city;
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }
}
