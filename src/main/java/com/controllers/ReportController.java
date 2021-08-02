package com.controllers;


import com.DTO.ReportsDTO;
import com.entity.CustomUserDetails;
import com.entity.Reports;
import com.entity.Users;
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
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/report")
public class ReportController {

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

    @Autowired
    UsersService usersService;

    @GetMapping(value = "/list", produces = "application/json")
    public List<ReportsDTO> repost() {
        List<Reports> reportsList = reportService.findAll();
        List<ReportsDTO> reportsDTOList = reportsList.stream()
                .map(report -> new ReportsDTO(report)).collect(Collectors.toList());
        return reportsDTOList;
    }

    @GetMapping("/view/{id}")
    public ReportsDTO viewReport(){
        return null;
    }

    @PostMapping(value = "/create", produces = "application/json")
    public ResponseEntity<Reports> ReadReport(@RequestBody ReportsDTO reportDto) throws ParseException {
        Reports reports = new Reports(reportDto);
        reports.setUsers(usersService.getUserLogin());
        reports.setDateCreated(new Date());
        return new ResponseEntity<>(reportService.save(reports), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity<Reports> ReadReport(@RequestBody ReportsDTO reportDto, @PathVariable("id") int id) throws ParseException {
        Reports reports = new Reports(reportDto);
        reports.setId(id);
        String role = "ADMIN";
        if (role.equals("ADMIN") || !reportDto.getStatus()) {
            reports.setDateRead(new Date());
        }
        return new ResponseEntity<>(reportService.save(reports), HttpStatus.OK);
    }
}
