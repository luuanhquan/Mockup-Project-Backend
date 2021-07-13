package com.service;

import com.entity.FileProject;
import com.repositories.FileProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileProjectService {

    @Autowired
    FileProjectRepository repository;

    public FileProject save(FileProject s) {
        return (FileProject) repository.findAll();
    }


    public List<FileProject> findAll() {
        return repository.findAll();
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public Optional<FileProject> findById(Integer id) {
        return repository.findById(id);
    }
}
