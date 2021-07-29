package com.service;

import com.entity.IssueChangeLog;
import com.repositories.IssueChangeLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class IssueChangeLogService {

    @Autowired
    IssueChangeLogRepository repository;

    public IssueChangeLog save(IssueChangeLog s) {
        return repository.save(s);
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
}
