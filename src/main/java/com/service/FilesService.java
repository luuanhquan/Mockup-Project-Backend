package com.service;

import com.entity.Files;
import com.repositories.FilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FilesService {

    @Autowired
    FilesRepository repository;

    public Files save(Files s) {
        return repository.save(s);
    }


    public List<Files> findAll() {
        return repository.findAll();
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public Optional<Files> findById(Integer id) {
        return repository.findById(id);
    }
}
