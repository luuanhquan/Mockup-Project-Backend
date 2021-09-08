package com.service;

import com.DTO.IssueDTO;
import com.DTO.IssueModelData;
import com.entity.Issues;
import com.repositories.IssueChangeLogRepository;
import com.repositories.IssueRepository;
import com.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    IssueChangeLogRepository issueChangeLogRepository;

    @Autowired
    FileIssueService fileIssueService;
    private static final IssueModelData modelData= new IssueModelData();

    public Issues save(Issues s) {
        return (Issues) issueRepository.findAll();
    }


    public List<Issues> findAll() {
        return issueRepository.findIssuesRoot();
    }


    public void deleteById(Integer id) {
        issueRepository.deleteById(id);
    }


    public IssueDTO findById(Integer id) {

        IssueDTO issueDTO= issueRepository.findByIssueId(id).stream().findFirst().orElse(null);
//
        if(!ObjectUtil.isEmpty(issueDTO)){
            issueDTO.setSubs(issueChangeLogRepository.findSubs(issueDTO.getId()));
            issueDTO.setFiles(fileIssueService.findByIssueId(issueDTO.getId()));
            issueDTO.setModelData(this.modelData);
            issueDTO.setIssueChangeLogDTO(issueChangeLogRepository.findByIssueId(issueDTO.getId()));
        }
        return issueDTO;
    }
}
