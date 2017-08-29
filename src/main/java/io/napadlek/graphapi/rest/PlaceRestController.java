package io.napadlek.graphapi.rest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PlaceRestController {

    @RequestMapping(path = "/{country}/{city}/{name}", method = RequestMethod.GET)
    public String getPlaceLocation(@PathVariable String country, @PathVariable String city, @PathVariable String name) {
        return country + city + name;
    }
}
