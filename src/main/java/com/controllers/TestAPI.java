package com.controllers;

import com.entity.Users;
import com.service.DivisionService;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/")
public class TestAPI {

    @Autowired
    UsersService service;
    @Autowired
    DivisionService divisionService;

    @GetMapping(value = "home",produces = "application/json")
    public List<Users> test(){
        Users admin= new Users().builder()
                .birthday(new Date())
                .dateCreated(new Date())
                .email("aaa")
                .password("123").role(1).username("admin").phone("012").firstname("1").lastname("1").type(1).status(0).id(1).build();
        System.out.println(admin);
        service.registerNewUserAccount(admin);
        return  service.findAll();
    }
}
