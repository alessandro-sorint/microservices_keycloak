package com.example.carservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

    private final static Logger log = LoggerFactory.getLogger(HomeController.class);

    /*@GetMapping("/home")
    public String howdy(Principal principal) {
        String username = principal.getName();
        return "Hello, " + username;
    }*/
    
    @GetMapping("/home")
    public String howdy(String name) {
        return "Hello, " + name;
    }
}
