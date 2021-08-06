package com.controllers;

import com.entity.Users;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@Controller
public class RegisterController {

    @Autowired
    private UsersService usersService;

    @PostMapping(path = "/register", produces = "application/json")
    public ResponseEntity registerNewUser(@RequestBody com.DTO.UserDTO userDTO) throws ParseException {
        Users users = new Users().loadFromDTO2(userDTO);
        if (usersService.checkDuplicate(users)) {
            return new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
        }
        usersService.save(users);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
