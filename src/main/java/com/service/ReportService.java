package com.service;

import com.entity.Reports;
import com.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    ReportRepository repository;

    public Reports save(Reports s) {
        return (Reports) repository.findAll();
    }


    public List<Reports> findAll() {
        return repository.findAll();
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public Optional<Reports> findById(Integer id) {
        return repository.findById(id);
    }
}
