package com.controllers;

import com.entity.Users;
import com.enums.EMAIL_TYPE;
import com.service.EmailService;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UsersService usersService;
    @Autowired
    EmailService emailService;

    @GetMapping(path = "/auth")
    public Users basicauth() {
       return usersService.getUserLogin();
    }

    @GetMapping(path = "/forgot-password/{email}")
    public void forgotPassword(@PathVariable("email")String email){
        if(usersService.findUserByEmail(email)!=null) {
            emailService.sendEmail(email, EMAIL_TYPE.FORGOT);
        }
    }
}
