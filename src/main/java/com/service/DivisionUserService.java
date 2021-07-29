package com.service;

import com.entity.DivisionUser;
import com.repositories.DivisionUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@Transactional
public class DivisionUserService {

    @Autowired
    DivisionUserRepository repository;

    public DivisionUser save(DivisionUser s) {
        return repository.save(s);
    }


    public List<DivisionUser> findAll() {
        return repository.findAll();
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public Optional<DivisionUser> findById(Integer id) {
        return repository.findById(id);
    }


//    public Division findByUserId(Users user) {
//        return  repository.findAllByUsers(user).get(0);
//    }

//    public Users findBoss(Division division, Boolean isManager){
//        return repository.findAllByDivisionAndIsManager(division,true).get(0);
//    }
}
