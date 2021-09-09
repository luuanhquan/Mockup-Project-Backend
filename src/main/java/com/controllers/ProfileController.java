package com.controllers;


import com.DTO.UserDTO;
import com.entity.Users;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


public class ProfileController {
    @Autowired
    UsersService usersService;





//Update
    @PutMapping(value = "/profile", produces = "application/json")
    public ResponseEntity<Users> updateProfile(@RequestBody UserDTO user) {
       Users users = usersService.update(user);
        return new ResponseEntity<>(users, HttpStatus.OK);

    }


}
