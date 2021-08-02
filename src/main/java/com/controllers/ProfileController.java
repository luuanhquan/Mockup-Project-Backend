package com.controllers;


import com.dto.UserDTO;
import com.entity.Users;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;

@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    UsersService usersService;

    @PutMapping(value = "/profile")
        public ResponseEntity<Users> getProfile(@RequestBody UserDTO userDTO, @PathVariable("username")String  username) throws ParseException {
            Users users= new Users().loadFromDTO(userDTO);
            users.setUsername(username);
        return new ResponseEntity<>(usersService.save(users), HttpStatus.OK);
    }





    @PutMapping(value = "/update/{username}" ,produces ="application/json" )
    public ResponseEntity<Users> updateProfile(@RequestBody UserDTO userDTO, @PathVariable("username")String  username) throws ParseException {
        Users users= new Users().loadFromDTO(userDTO);
        users.setUsername(username);
        return new ResponseEntity<>(usersService.save(users), HttpStatus.OK);
    }

}
