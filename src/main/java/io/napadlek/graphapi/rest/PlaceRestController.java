package io.napadlek.graphapi.rest;

import io.napadlek.graphapi.business.data.PagingDO;
import io.napadlek.graphapi.business.service.PlaceLocationDO;
import io.napadlek.graphapi.business.service.PlaceQueryDO;
import io.napadlek.graphapi.business.service.PlaceService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
public class PlaceRestController {

    private static final String FACEBOOK_CONNECT_PATH = "/connect/facebook";
    private static final Log LOG = LogFactory.getLog(PlaceRestController.class);

    private final ConnectionRepository connectionRepository;
    private final PlaceService placeService;

    @Autowired
    public PlaceRestController(ConnectionRepository connectionRepository, PlaceService placeService) {
        this.connectionRepository = connectionRepository;
        this.placeService = placeService;
    }

    @GetMapping(path = "/{country}/{city}/{name}")
    public ResponseEntity getPlaceLocation(@PathVariable String country, @PathVariable String city, @PathVariable String name,
                                                                 HttpServletResponse response) throws IOException {
        if (!isFacebookAuthorised()) {
            LOG.info("Facebook not yet authorised, redirecting to authorisation page.");
            response.sendRedirect(FACEBOOK_CONNECT_PATH);
            return null;
        }

        List<PlaceLocationDO> placeLocations = placeService.getPlaceLocations(new PlaceQueryDO(country, city, name));
        if (placeLocations.size() == 1) {
            return ResponseEntity.ok(placeLocations.get(0));
        }
        return ResponseEntity.ok().body(placeLocations);
    }

    private boolean isFacebookAuthorised() throws IOException {
        return connectionRepository.findPrimaryConnection(Facebook.class) != null;
    }
}
