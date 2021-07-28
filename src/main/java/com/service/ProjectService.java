package com.service;

import com.entity.Projects;
import com.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    //Tìm all project
    public List<Projects> findAll() {
        return projectRepository.findAll();
    }

    //Tìm theo id project
    public Projects findbyProjects(Integer id) {
        return projectRepository.findById(id)
                .orElse(null);
    }

    //Add project
    public Projects addProject(Projects projects){
        return  projectRepository.save(projects);
    }

    //Edit Project
    public Projects updateProject(Projects projects) {
        return  projectRepository.saveAndFlush(projects);
    }

    //Delete Project
    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }



}
