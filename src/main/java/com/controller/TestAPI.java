package com.controller;

import com.entity.Division;
import com.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class TestAPI {

    @Autowired
    DivisionService divisionService;

    @GetMapping("home")
    public List<Division> test(){
        return divisionService.findAll();
    }
}
