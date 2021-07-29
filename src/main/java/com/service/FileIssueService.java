package com.service;

import com.entity.FileIssue;
import com.repositories.FileIssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FileIssueService {

    @Autowired
    FileIssueRepository repository;

    public FileIssue save(FileIssue s) {
        return repository.save(s);
    }


    public List<FileIssue> findAll() {
        return repository.findAll();
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public Optional<FileIssue> findById(Integer id) {
        return repository.findById(id);
    }
}
