package io.napadlek.graphapi.rest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.GraphApi;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@RestController
public class PlaceRestController {

    private Log LOG = LogFactory.getLog(PlaceRestController.class);

    private GraphApi facebook;
    private ConnectionRepository connectionRepository;

    @Autowired
    public PlaceRestController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }

    @RequestMapping(path = "/{country}/{city}/{name}", method = RequestMethod.GET)
    public String getPlaceLocation(@PathVariable String country, @PathVariable String city, @PathVariable String name,
                                   HttpServletResponse response) throws IOException {
        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
            response.sendRedirect("/connect/facebook");
            return null;
        }
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("q", "egnyte");
        multiValueMap.add("type", "place");
        String s = facebook.fetchObject("search", String.class, multiValueMap);
        LOG.info(s);
        return country + city + name;
    }
}
