package com.controllers;


import com.DTO.ProjectCreateDTO;
import com.DTO.ProjectDetailDTO;
import com.entity.Projects;
import com.enums.ACTIVE_STATUS;
import com.service.ProjectService;
import com.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project")
public class ProjectDetailController {
    @Autowired
    public final ProjectService projectService;

    public ProjectDetailController(ProjectService projectService) {
        this.projectService = projectService;
    }

    //tìm all Project
    @GetMapping("/list")
    public List<ProjectDetailDTO> getAllProject() {
        List<Projects> projects = projectService.findAll();
        List<ProjectDetailDTO> list = projects.stream().map(projectItem -> new ProjectDetailDTO().loadFromEntity(projectItem)).collect(Collectors.toList());
        return list;
    }


    //Tìm Project theo id
    @GetMapping("/view/{id}")
    public ResponseEntity<Projects> getProjectById(@PathVariable("id") Integer id) {
        Projects project = projectService.findbyProjects(id);
        if (ObjectUtil.isEmpty(projectService)) return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }


    //Thêm Project
    @PostMapping("/create")
    public ResponseEntity addProject(@RequestBody ProjectCreateDTO projectsDTO) throws ParseException {
        if (ObjectUtil.isEmpty(projectsDTO)) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Projects project = new Projects().loadFromDTO(projectsDTO);
        projectService.addProject(project);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //update project
    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity ReadProject(@RequestBody ProjectDetailDTO projectDetailDTO, @PathVariable("id") int id) throws ParseException {
        Projects project = projectService.findbyProjects(id);
        if (ObjectUtil.isEmpty(project)) return new ResponseEntity(HttpStatus.NOT_FOUND);
        projectService.updateProject(project.loadFromDTO(projectDetailDTO));
        return new ResponseEntity(null, HttpStatus.OK);
    }

    //Xóa Project theo id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Integer id) {
        Projects project = projectService.findbyProjects(id);
        if (ObjectUtil.isEmpty(project)) return new ResponseEntity(HttpStatus.NOT_FOUND);
        project.setStatus(ACTIVE_STATUS.INACTIVE);
        projectService.updateProject(project);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
