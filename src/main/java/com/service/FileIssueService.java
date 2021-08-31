package com.service;

import com.DTO.FileDTO;
import com.entity.FileIssue;
import com.repositories.FileIssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FileIssueService {

    @Autowired
    FileIssueRepository repository;

    public FileIssue save(FileIssue s) {
        return (FileIssue) repository.findAll();
    }


    public List<FileIssue> findAll() {
        return repository.findAll();
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


    public Optional<FileIssue> findById(Integer id) {
        return repository.findById(id);
    }

    public List<FileDTO> findByIssueId(int id) {
        return repository.findByIssueId(id);
    }
}
