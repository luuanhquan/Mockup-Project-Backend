package com.service;

import com.entity.TimeLog;
import com.repositories.TimeLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeLogService {

    @Autowired
    TimeLogRepository repository;

    public TimeLog save(TimeLog s) {
        return (TimeLog) repository.findAll();
    }


    public List<TimeLog> findAll() {
        return repository.findAll();
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public Optional<TimeLog> findById(Integer id) {
        return repository.findById(id);
    }
}
