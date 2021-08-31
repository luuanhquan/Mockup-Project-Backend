package com.service;

import com.DTO.SimpleIssue;
import com.entity.IssueChangeLog;
import com.repositories.IssueChangeLogRepository;
import com.sun.xml.internal.stream.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueChangeLogService {

    @Autowired
    IssueChangeLogRepository repository;

    public IssueChangeLog save(IssueChangeLog s) {
        return (IssueChangeLog) repository.findAll();
    }


    public List<IssueChangeLog> findAll() {
        return repository.findAll();
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public Optional<IssueChangeLog> findById(Integer id) {
        return repository.findById(id);
    }

    public List<SimpleIssue> findSubs(int id) { return repository.findSubs(id);
    }
}
