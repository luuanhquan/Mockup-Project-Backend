package com.service;

import com.entity.RequestType;
import com.repositories.RequestTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RequestTypeService {

    @Autowired
    RequestTypeRepository repository;

    public RequestType save(RequestType s) {
        return repository.save(s);
    }


    public List<RequestType> findAll() {
        return repository.findAll();
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public Optional<RequestType> findById(Integer id) {
        return repository.findById(id);
    }
}
