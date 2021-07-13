package com.service;

import com.entity.Users;
import com.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    UsersRepository repository;

    public Users save(Users s) {
        return (Users) repository.findAll();
    }


    public List<Users> findAll() {
        return repository.findAll();
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public Optional<Users> findById(Integer id) {
        return repository.findById(id);
    }
}
