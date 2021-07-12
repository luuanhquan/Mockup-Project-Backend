package com.service;

import com.repositories.DivisionRepository;
import com.entity.Division;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class DivisionService {

    @Autowired
    DivisionRepository repository;

    public Division save(Division s) {
        return (Division) repository.findAll();
    }


    public List<Division> findAll() {
        return repository.findAll();
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public Optional<Division> findById(Integer id) {
        return repository.findById(id);
    }
}
