package io.napadlek.graphapi.business.service;

import java.util.List;

public interface PlaceService {

    List<PlaceLocationDO> getPlaceLocations(PlaceQueryDO placeQuery);
}

