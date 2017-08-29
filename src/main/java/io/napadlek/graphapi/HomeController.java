package io.napadlek.graphapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;

@Controller
public class HomeController {

    private Facebook facebook;
    private ConnectionRepository connectionRepository;
    private ConnectController connectController;

    @Autowired
    public HomeController(Facebook facebook, ConnectionRepository connectionRepository) {
        this.facebook = facebook;
        this.connectionRepository = connectionRepository;
    }


}
