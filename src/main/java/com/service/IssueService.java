package com.service;

import com.entity.Issues;
import com.repositories.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueService {

    @Autowired
    IssueRepository repository;

    public Issues save(Issues s) {
        return (Issues) repository.findAll();
    }


    public List<Issues> findAll() {
        return repository.findIssuesRoot();
    }




    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public Optional<Issues> findById(Integer id) {
        return repository.findById(id);
    }
}
