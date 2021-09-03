package com.controllers;


import com.DTO.ProjectDetailDTO;
import com.DTO.ReportsDTO;
import com.DTO.UserDTO;
import com.entity.Projects;
import com.entity.Reports;
import com.entity.Users;
import com.service.UsersService;
import com.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UsersService usersService;


    //    all User
    @GetMapping("/all")
    public List<UserDTO> getAllEmployees() {

        List<Users> users = usersService.findAll();
        List<UserDTO> usersDTOS = new ArrayList<>();
        for (Users u : users) {
            UserDTO DTO = new UserDTO();
            DTO.loadFromEntity(u);
            usersDTOS.add(DTO);
        }
        return usersDTOS;
    }


    ////Get Profile
    @GetMapping(value = "profile/{id}" ,  produces = "application/json")
    public ResponseEntity<UserDTO> getProfile(@PathVariable("id") Integer id)  {
        Users users = usersService.findById(id);
        return new ResponseEntity<>(new UserDTO().loadFromEntity(users), HttpStatus.OK);

    }
    //Update Profile
    @PutMapping(value = "profile/update", produces = "application/json")
//    public ResponseEntity<Users> updateProfile(@RequestBody UserDTO dto) throws ParseException {
//        Users updateUser = new Users();
//        updateUser.setUsername(usersService.getUserLogin().getUsername());
//        updateUser.loadFromDTO(dto);
//        return new ResponseEntity<>(usersService.save(updateUser), HttpStatus.OK);
//    }
    public ResponseEntity<Users> updateProfile(@RequestBody UserDTO dto) throws ParseException {
        Users users = usersService.update(dto);
        return new ResponseEntity<>(users, HttpStatus.OK);}


    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateUser(@PathVariable("id") Integer id, @RequestBody UserDTO dto) throws ParseException{
        Users updateUser = usersService.findById(id);
        updateUser.setUsername(usersService.getUserLogin().getUsername());
        updateUser.loadFromDTO(dto);
        usersService.save(updateUser);
        return new ResponseEntity<>(updateUser, HttpStatus.OK);
    }
/*

 */

}
