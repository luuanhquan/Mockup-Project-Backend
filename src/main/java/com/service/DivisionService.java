package com.service;

import com.entity.Division;
import com.DTO.DivisionList;
import com.repositories.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class DivisionService {

    @Autowired
    DivisionRepository repository;


    public Division save(Division s) {
        return (Division) repository.findAll();
    }


    public List<Division> findAll() {
        return repository.findAll();
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public Optional<Division> findById(Integer id) {
        return repository.findById(id);
    }

    public List<DivisionList> getDivisionList() {
        List<DivisionList> divisionLists= repository.getDivision().stream().map(div-> {
            div.setPMList(repository.getPM(div.getId()));
            return div;
        }).collect(Collectors.toList());
        return divisionLists;
    }
}
