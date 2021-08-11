package com.DTO;

import com.entity.IssueChangeLog;
import com.entity.Issues;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class IssueDTO {
    int id;
    String creator;
    String assignee;
    String status;
    List<IssueDetailDTO> issueChangeLogDTO;
    List<FileDTO> files = new ArrayList<>();
    SimpleIssue parent ;
    List<SimpleIssue> subs = new ArrayList<>();
    IssueModelData modelData;


    public IssueDTO loadByIssue(Issues issue) {
        this.id = issue.getId();
        this.creator = issue.getUserCreated().getUsername();
        this.assignee = issue.getAssignee().getUsername();
        this.status = issue.getStatus().name();
        System.out.println(issue.getIssueChangeLogList());
        this.issueChangeLogDTO = issue.getIssueChangeLogList().stream()
                .map(item -> new IssueDetailDTO().loadFromIssueChangeLog(item))
                .collect(Collectors.toList());

        issue.getFileIssuesById().stream()
                .forEach(file -> files.add(new FileDTO(file.getFile().getId(), file.getFile().getName(), file.getFile().getPath())));
        if (issue.getParent() != null) {
            this.parent= new SimpleIssue().loadFromIssue(issue.getParent());
        }
            issue.getIssueList().stream().forEach(issues -> this.subs.add(new SimpleIssue().loadFromIssue(issues)));
        modelData=new IssueModelData();

        return this;
    }
}
