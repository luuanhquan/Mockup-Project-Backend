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



    public List<Projects> findAll() {
        return projectRepository.findAll();
    }

//    public Projects findbyProjects(Integer id) {
//        return projectRepository.findById(id)
//                .orElseThrow(() -> new IllegalStateException("Không có id:" + id + ". Mời bạn nhập lại"));
//    }

    public Projects findbyProjects(Integer id) {
        return projectRepository.findById(id)
                .orElse(null);
    }

//    public Projects addProject(Projects projects){
//        projects.setPersonId(UUID.randomUUID().toString());
//        return  projectRepository.save(projects);
//    }

    public Projects save(Projects s) {
        return (Projects) projectRepository.findAll();
    }

    public void deleteProject(Integer id) {
        projectRepository.deleteById(id);
    }



}
