package com.service;

import com.entity.Projects;
import com.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectService {

    @Autowired
    ProjectRepository repository;

    public Projects save(Projects s) {
        return repository.save(s);
    }


    public List<Projects> findAll() {
        return repository.findAll();
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public Optional<Projects> findById(Integer id) {
        return repository.findById(id);
    }
}
