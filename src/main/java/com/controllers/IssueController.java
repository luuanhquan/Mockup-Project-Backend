package com.controllers;

import com.DTO.IssueDTO;
import com.entity.IssueChangeLog;
import com.entity.Issues;
import com.service.IssueChangeLogService;
import com.service.IssueService;
import com.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/issue")
public class IssueController {
    @Autowired
    IssueService issueService;

    @Autowired
    IssueChangeLogService issueChangeLogService;

    @GetMapping("/{id}")
    public ResponseEntity getIssue(@PathVariable("id")int issueId){
        IssueDTO issueDTO= issueService.findById(issueId);
        if(ObjectUtil.isEmpty(issueDTO))
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        else return new ResponseEntity(issueDTO,HttpStatus.OK);
    }
}
