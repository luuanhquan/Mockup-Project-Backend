package com.controllers;


import com.DTO.ProjectDTO;
import com.entity.Projects;
import com.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project")
public class ProjectController {
    @Autowired
    public final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    //tìm all Project
    @GetMapping("/list")
    public List<ProjectDTO> getAllProject() {
        List<Projects> projects = projectService.findAll();
        List<ProjectDTO> list = projects.stream().map(projectItem -> new ProjectDTO(projectItem)).collect(Collectors.toList());
//        System.out.println(projects);
        return list;
    }



    //Tìm Project theo id
    @GetMapping("/view?id=?")
    public ResponseEntity<Projects> getProjectById(@PathVariable("id") Integer id) {
        Projects project = projectService.findbyProjects(id);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    //Thêm Project
    @PostMapping("/create-pj")
    public ResponseEntity<Projects> addProject(@RequestBody Projects projects) {
        System.out.println(projects);
        Projects newProject = projectService.addProject(projects);
        return new ResponseEntity<>(newProject, HttpStatus.CREATED);
    }

    //edit project
    @PutMapping("/edit")
    public ResponseEntity<Projects> updateProject(@RequestBody Projects projects) {
        Projects updateProject = projectService.updateProject(projects);
        return new ResponseEntity<>(updateProject, HttpStatus.OK);
    }

    //Xóa Project theo id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Integer id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}