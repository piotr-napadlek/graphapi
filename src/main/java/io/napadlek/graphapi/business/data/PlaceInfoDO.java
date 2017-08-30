package io.napadlek.graphapi.business.data;

public class PlaceInfoDO {
    private String id;
    private String name;
    private LocationDO location;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationDO getLocation() {
        return location;
    }

    public void setLocation(LocationDO location) {
        this.location = location;
    }
}
