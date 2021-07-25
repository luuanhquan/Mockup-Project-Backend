package com.controllers;

import com.configs.AuthenticationBean;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Basic;

@RestController
public class UserController {

    @GetMapping(path = "/auth")
    public AuthenticationBean basicauth() {
        return new AuthenticationBean("You are authenticated");
    }

}
