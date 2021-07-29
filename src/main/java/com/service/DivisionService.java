package com.service;

import com.entity.Division;
import com.repositories.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@Transactional
public class DivisionService{

    @Autowired
    DivisionRepository repository;

    public Division save(Division s) {
        return repository.save(s);
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
