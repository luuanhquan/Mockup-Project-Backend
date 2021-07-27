package com.controllers;



import com.entity.Projects;
import com.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("${spring.data.rest.base-path}/Project")
@RequestMapping( "/project")
public class ProjectController {
    @Autowired
    public final ProjectService projectService;
//8080/project/al
//8080/project/update/project1

//    public ProjectController(@RequestBody ProjectService projectService) {
//        this.projectService = projectService;
//    }

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping( "/all")
    public ResponseEntity<List<Projects>> getAllProject () {
        List<Projects> projects = projectService.findAll();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/findProject/{id}")
    public ResponseEntity<Projects> getProjectById (@PathVariable("id") Integer id){
        Projects project = projectService.findbyProjects(id);
        return  new ResponseEntity<>(project,HttpStatus.OK);
    }

//    @PostMapping("/add")
//    public ResponseEntity<Projects> addProject (Projects projects){
//        System.out.println(projects);
//        Projects newProject = projectService.addProject(projects);
//        return  new ResponseEntity<>(newProject,HttpStatus.CREATED);
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Integer id) {
        projectService.deleteProject(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //    @PostMapping("/add")
//    public ResponseEntity<Emloyee> addEmployee(@RequestBody Emloyee employee) {
//        System.out.println(employee);
//        Emloyee newEmployee =emloyeService.addemloyee(employee);
//        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<Emloyee> updateEmployee(@RequestBody Emloyee employee) {
//        Emloyee updateEmployee = emloyeService.updateEmployee(employee);
//        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id) {
//        emloyeService.deleteEmployee(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}