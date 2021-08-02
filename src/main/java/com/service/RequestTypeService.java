package com.service;

import com.entity.RequestType;
import com.repositories.RequestTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestTypeService {

    @Autowired
    RequestTypeRepository repository;

    public RequestType save(RequestType s) {
        return (RequestType) repository.findAll();
    }


    public List<RequestType> findAll() {
        return repository.findAll();
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public RequestType findByName(String name) {
        return repository.findByName(name);
    }


    public Optional<RequestType> findById(Integer id) {
        return repository.findById(id);
    }
}
