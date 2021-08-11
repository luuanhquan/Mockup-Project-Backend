package com.controllers;


import com.DTO.ProjectCreateDTO;
import com.DTO.ProjectDetailDTO;
import com.entity.Projects;
import com.enums.ACTIVE_STATUS;
import com.service.ProjectService;
import com.utils.ObjectUtil;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.init.ResourceReader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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
        List<Projects> projects = projectService.findAllActive();
        List<ProjectDetailDTO> list = projects.stream().map(projectItem -> new ProjectDetailDTO().loadFromEntity(projectItem)).collect(Collectors.toList());
        return list;
    }

    //Tìm Project theo id
    @GetMapping("{id}")
    public ResponseEntity getProjectById(@PathVariable("id") Integer id) {
        Projects project = projectService.findbyProjects(id);
        if (ObjectUtil.isEmpty(project))
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        return new ResponseEntity(project, HttpStatus.OK);
    }

    //Thêm mới Project
    @PostMapping("/create")
    public ResponseEntity addProject(@RequestBody ProjectCreateDTO projectsDTO) throws ParseException {
        System.out.println(projectsDTO);
        if (ObjectUtil.isEmpty(projectsDTO)) return new ResponseEntity(HttpStatus.BAD_REQUEST);
        Projects project = new Projects().loadFromDTOCreate(projectsDTO);
        project.setStatus(ACTIVE_STATUS.ACTIVE);
        projectService.addProject(project);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    //update project
//    @PutMapping(value = "/update/{id}", produces = "application/json")
//    public ResponseEntity ReadProject(@RequestBody ProjectDetailDTO projectDetailDTO, @PathVariable("id") int id) throws ParseException {
//        Projects project = projectService.findbyProjects(id);
//        if (ObjectUtil.isEmpty(project)) return new ResponseEntity(HttpStatus.NOT_FOUND);
//        projectService.updateProject(project.loadFromDTODetail(projectDetailDTO));
//        return new ResponseEntity(null, HttpStatus.OK);
//    }

    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity ReadProject(@RequestBody ProjectCreateDTO projectUpdateDTO, @PathVariable("id") int id) throws ParseException {
        Projects project = projectService.findbyProjects(id);
        if (ObjectUtil.isEmpty(project)) return new ResponseEntity(HttpStatus.NOT_FOUND);
        projectService.updateProject(project.loadFromDTOCreate(projectUpdateDTO));
        return new ResponseEntity(null, HttpStatus.OK);
    }

    //Xóa Project theo id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProject(@PathVariable("id") Integer id) {
        Projects project = projectService.findbyProjects(id);
        if (ObjectUtil.isEmpty(project)) return new ResponseEntity(HttpStatus.NOT_FOUND);
        project.setStatus(ACTIVE_STATUS.INACTIVE);
        projectService.updateProject(project);
        return new ResponseEntity<>(new String("Thành công"), HttpStatus.OK);
    }

    //delete cách 2
//    @DeleteMapping( "/delete/{id}")
//    public ResponseEntity<Projects> deleteProject(
//            @PathVariable("id") Integer id) {
//        Optional<Projects> projects = projectService.findById(id);
//        if (!projects.isPresent()) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        projectService.remove(projects.get());
//        return new ResponseEntity<>(HttpStatus.OK);
//    }


}
