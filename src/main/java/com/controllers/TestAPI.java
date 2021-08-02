package com.controllers;

import com.entity.Issues;
import com.repositories.DivisionUserRepository;
import com.repositories.UsersRepository;
import com.service.DivisionService;
import com.service.IssueService;
import com.service.ProjectService;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Autowired
    ProjectService projectService;

    @Autowired
    IssueService issueService;

    @GetMapping(value = "home",produces = "application/json")
    public List<Issues> test(){
        return issueService.findAll();
    }
}
