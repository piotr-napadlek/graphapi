package io.napadlek.graphapi.business.service.impl;

import io.napadlek.graphapi.business.data.PlaceRepository;
import io.napadlek.graphapi.business.data.PlaceInfoDO;
import io.napadlek.graphapi.business.service.BusinessException;
import io.napadlek.graphapi.business.service.PlaceLocationDO;
import io.napadlek.graphapi.business.service.PlaceQueryDO;
import io.napadlek.graphapi.business.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {
    private PlaceRepository placeRepository;

    @Autowired
    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }


    @Override
    public List<PlaceLocationDO> getPlaceLocations(PlaceQueryDO placeQuery) {
        List<PlaceInfoDO> placesByName = placeRepository.getPlacesByName(placeQuery.getName());
        List<PlaceLocationDO> filteredPlaceCollections = placesByName.stream()
                .filter(matchesCityAndCountryCriteria(placeQuery))
                .map(place -> new PlaceLocationDO(place.getName(), place.getLocation()))
                .collect(Collectors.toList());
        if (filteredPlaceCollections.isEmpty()) {
            throw new BusinessException("No results found, try less specific parameters!");
        }
        return filteredPlaceCollections;
    }

    private Predicate<PlaceInfoDO> matchesCityAndCountryCriteria(final PlaceQueryDO placeQuery) {
        return place -> placeQuery.getCity().trim().equalsIgnoreCase(place.getLocation().getCity()) &&
                placeQuery.getCountry().trim().equalsIgnoreCase(place.getLocation().getCountry());
    }
}
