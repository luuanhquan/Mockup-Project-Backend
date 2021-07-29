package com.controllers;

import com.entity.Users;
import com.repositories.DivisionUserRepository;
import com.repositories.UsersRepository;
import com.service.DivisionService;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/")
public class TestAPI {

    @Autowired
    UsersService service;

    @Autowired
    UsersRepository repository;

    @Autowired
    DivisionService divisionService;

    @Autowired
    DivisionUserRepository divisionUserRepository;

    @GetMapping(value = "home",produces = "application/json")
    public Users test(){
        return  repository.findByUsername("admin");
    }
}
