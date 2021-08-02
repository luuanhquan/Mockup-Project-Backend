package com.controllers;

import com.DTO.AuthenticationBean;
import com.entity.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping(path = "/auth")
    public AuthenticationBean basicauth() {
        CustomUserDetails auth= (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new AuthenticationBean(auth.getUser());
    }


}
