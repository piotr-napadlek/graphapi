package io.napadlek.graphapi.business.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class PlaceRepository {
    private GraphApi graphApi;

    @Autowired
    public PlaceRepository(GraphApi graphApi) {
        this.graphApi = graphApi;
    }

    public List<PlaceInfoDO> getPlacesByName(String name) {
        final MultiValueMap<String, String> searchParams = new LinkedMultiValueMap<>();
        final List<PlaceInfoDO> resultPlaces = new LinkedList<>();
        PlaceSearchResultDO searchResult = new PlaceSearchResultDO();
        searchParams.set("q", name);
        searchParams.set("type", "place");
        searchParams.set("limit", "100");
        searchParams.set("fields", "location,name");
        do {
            searchParams.set("after", searchResult.getAfterCursor().orElse(""));
            searchResult = findPlaces(searchParams);
            resultPlaces.addAll(searchResult.getData());
        } while (searchResult.getAfterCursor().isPresent());

        return resultPlaces;
    }

    private PlaceSearchResultDO findPlaces(MultiValueMap<String, String> searchParams) {
        return graphApi.fetchObject("search", PlaceSearchResultDO.class, searchParams);
    }
}
