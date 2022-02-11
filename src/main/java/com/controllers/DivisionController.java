package com.controllers;

import com.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/division")
public class DivisionController {
    @Autowired
    DivisionService divisionService;

    @GetMapping("")
    public ResponseEntity getDivisionList(){
        return new ResponseEntity(divisionService.getDivisionList(), HttpStatus.OK);
    }

}
