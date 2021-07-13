package com.service;

import com.entity.LeaveRequests;
import com.repositories.LeaveRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveRequestService {

    @Autowired
    LeaveRequestRepository repository;

    public LeaveRequests save(LeaveRequests s) {
        return (LeaveRequests) repository.findAll();
    }


    public List<LeaveRequests> findAll() {
        return repository.findAll();
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public Optional<LeaveRequests> findById(Integer id) {
        return repository.findById(id);
    }
}
