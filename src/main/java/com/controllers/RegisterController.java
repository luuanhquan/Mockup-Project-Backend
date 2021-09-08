package com.controllers;

import com.DTO.RegisterUser;
import com.entity.Users;
import com.repositories.UsersRepository;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@CrossOrigin(origins = "http://localhost:4200/team1/")
public class RegisterController {
    @Autowired
    private UsersService usersService;

    @Autowired
    UsersRepository repository;
    @PostMapping(path = "/register", produces = "application/json")
    public ResponseEntity registerNewUser(@RequestBody @Valid RegisterUser registerUser)  {
        Users users = usersService.register(registerUser);
                if (usersService.checkDuplicate(users)) {
            return new ResponseEntity(HttpStatus.CREATED);
        }
         return ResponseEntity.badRequest().build();
    }

}


