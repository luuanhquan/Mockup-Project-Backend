package com.controllers;

import com.DTO.AuthenticationBean;
import com.DTO.ProjectDTO;
import com.entity.CustomUserDetails;
import com.entity.Users;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
