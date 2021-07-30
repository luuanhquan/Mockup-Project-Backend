package com.controllers;

import com.DTO.ReportsDTO;
import com.entity.Division;
import com.entity.Reports;
import com.entity.Users;
import com.repositories.DivisionUserRepository;
import com.repositories.UsersRepository;
import com.service.DivisionService;
import com.service.DivisionUserService;
import com.service.ReportService;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/all")
public class TestAPI {

    @Autowired
    UsersService service;

    @Autowired
    DivisionService divisionService;

    @Autowired
    ReportService reportService;


    @Autowired
    DivisionUserService divisionUserService;

    @GetMapping(value = "home",produces = "application/json")
    public void test(){
//        Users loginedUser= (Users) SecurityContextHolder.getContext().getAuthentication();
        Users loginedUser= service.findById(1000).orElse(null);
        Division userDivison= divisionUserService.findDivisionByUsers(loginedUser);
        Users manager = divisionUserService.findManager(userDivison);
        reportService.sendEmail(manager.getEmail(),"Test","Hello");
    }
    @GetMapping(value = "/report",produces ="application/json" )
    public List<ReportsDTO> repost(){
        List<Reports> reportsList= reportService.findAll();
        List<ReportsDTO> reportsDTOS= new ArrayList<>();
        for(Reports r: reportsList){
            reportsDTOS.add(new ReportsDTO(r));
        }
        return reportsDTOS;

    }
}
