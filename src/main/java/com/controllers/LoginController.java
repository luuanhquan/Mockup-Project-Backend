package com.controllers;

import com.entity.JwtRequest;
import com.entity.JwtResponse;
import com.enums.EMAIL_TYPE;
import com.service.AuthenticationService;
import com.service.EmailService;
import com.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;



    @PostMapping(path = "/auth", produces = "application/json")
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest jwtRequest) {
        JwtResponse response= authenticationService.authenticate(jwtRequest);
        if(ObjectUtil.isEmpty(response))return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/forgot-password/{email}")
    public void forgotPassword(@PathVariable("email") String email) {

    }
}
