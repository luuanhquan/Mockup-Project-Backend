package com.service;

import com.entity.ProjectUser;
import com.repositories.ProjectUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectUserService {

    @Autowired
    ProjectUserRepository repository;

    public ProjectUser save(ProjectUser s) {
        return (ProjectUser) repository.findAll();
    }


    public List<ProjectUser> findAll() {
        return repository.findAll();
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public Optional<ProjectUser> findById(Integer id) {
        return repository.findById(id);
    }
}
