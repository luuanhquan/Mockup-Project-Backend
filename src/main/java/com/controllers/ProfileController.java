package com.controllers;


import com.DTO.UserDTO;
import com.entity.Users;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


public class ProfileController {
    @Autowired
    UsersService usersService;

/*
//Get
@GetMapping("profile")
public ResponseEntity<Users> GetProfile(@PathVariable("username") String username ){
    Users users = usersService.findByUsername(username);
    return new ResponseEntity<>(new UserDTO().loadFromEntity(users), HttpStatus.OK);
}
 */


    @GetMapping(value = "profile/{id}" ,  produces = "application/json")
    public ResponseEntity<UserDTO> getEmployeeById(@PathVariable("id") Integer id) {
        Users users = usersService.findById(id);
        return new ResponseEntity<>(new UserDTO().loadFromEntity(users), HttpStatus.OK);
    }

//Update
//    @PutMapping(value = "profile/update", produces = "application/json")
//    public ResponseEntity<Users> updateProfile(@RequestBody UserDTO user) {
//       Users users = usersService.update(user);
//        return new ResponseEntity<>(users, HttpStatus.OK);
//
//    }


}
