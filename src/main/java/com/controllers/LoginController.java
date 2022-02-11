package com.controllers;

import com.DTO.ChangePassRequest;
import com.entity.JwtRequest;
import com.entity.JwtResponse;
import com.service.AuthenticationService;
import com.service.EmailService;
import com.service.UsersService;
import com.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsersService usersService;

    @PostMapping(path = "/auth", produces = "application/json")
    public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest jwtRequest) {
        JwtResponse response = authenticationService.authenticate(jwtRequest);
        if (ObjectUtil.isEmpty(response)) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok(response);
    }

    @PostMapping(path = "/forgot-password/")
    public void forgotPassword(@RequestBody String email) {
        this.emailService.forgotPass(email);
    }

    @GetMapping(path = "/forgot-password/{key}")
    public ResponseEntity checkKey(@PathVariable("key") String key) {
        if (this.emailService.checkKey(key))
            return new ResponseEntity(HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @PutMapping(path = "/forgot-password/")
    public ResponseEntity changePassword(@RequestBody ChangePassRequest request) {
        if (emailService.checkKey(request.getKey())) {
            usersService.changePass(request);
            return ResponseEntity.ok("Success");
        } else
            return ResponseEntity.badRequest().body("No service");
    }
}
