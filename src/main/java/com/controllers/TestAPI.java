package com.controllers;

import com.DTO.ReportsDTO;
import com.entity.Division;
import com.entity.Reports;
import com.entity.Users;
import com.repositories.DivisionUserRepository;
import com.repositories.UsersRepository;
import com.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Autowired
    ProjectService projectService;

    @Autowired
    IssueService issueService;

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
    @PutMapping(value = "/update/{id}" ,produces ="application/json" )
    public ResponseEntity<Reports> ReadReport(@RequestBody ReportsDTO reportDto, @PathVariable("id")int id) throws ParseException {
        Reports reports= new Reports(reportDto);
        reports.setId(id);
        String role="ADMIN";
        if(role.equals("ADMIN")&&!reportDto.getStatus()){
            reports.setDateRead(new Date());
        }
        return new ResponseEntity<>(reportService.save(reports), HttpStatus.OK);
    }

    @PostMapping(value = "/create" ,produces ="application/json" )
    public ResponseEntity<Reports> ReadReport(@RequestBody ReportsDTO reportDto) throws ParseException {
        Reports reports= new Reports(reportDto);
        reports.setDateCreated(new Date());
        return new ResponseEntity<>(reportService.save(reports), HttpStatus.OK);
    }

}
