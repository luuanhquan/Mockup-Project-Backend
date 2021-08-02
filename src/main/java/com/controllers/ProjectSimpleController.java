package com.controllers;

import com.DTO.ProjectSimpleDTO;
import com.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ProjectSimpleController {
    @Autowired
    ProjectService service;

    @GetMapping(path = "/project/all")
    public List<ProjectSimpleDTO> basicauth() {
        return service.findAll().stream().map(item -> new ProjectSimpleDTO(item)).collect(Collectors.toList());
    }
}
