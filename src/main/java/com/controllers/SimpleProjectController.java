package com.controllers;

import com.DTO.ProjectSimple;
import com.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SimpleProjectController {
    @Autowired
    ProjectService service;

        @GetMapping(path = "/project/all")
        public List<ProjectSimple> basicauth() {
            return service.findAll().stream().map(item->new ProjectSimple(item)).collect(Collectors.toList());
    }
}
