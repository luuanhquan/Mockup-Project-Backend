package com.service;

import com.entity.DivisionProject;
import com.repositories.DivisionProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DivisionProjectService {

    @Autowired
    DivisionProjectRepository repository;

    public DivisionProject save(DivisionProject s) {
        return (DivisionProject) repository.findAll();
    }


    public List<DivisionProject> findAll() {
        return repository.findAll();
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public Optional<DivisionProject> findById(Integer id) {
        return repository.findById(id);
    }
}
