package com.controllers;

import com.entity.Division;
import com.service.DivisionService;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class TestAPI {

    @Autowired
    UsersService service;
    @Autowired
    DivisionService divisionService;

    @GetMapping("home")
    public List<Division> test(){
        return divisionService.findAll();
    }
}
