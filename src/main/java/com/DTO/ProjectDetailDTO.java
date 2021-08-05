package com.DTO;

import com.entity.Issues;
import com.entity.ProjectUser;
import com.entity.Projects;
import com.entity.Users;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ProjectDetailDTO {
    private Integer id;
    private String name;
    private String description;
    private String dateStated;
    private String dateEnded;
    private String status;
    private List<Users> listMember;
    private long totalMember;
    private int totalIssues;
    private List<IssueDTO> listIssues;

    public ProjectDetailDTO loadFromEntity(Projects projects) {
        this.id = projects.getId();
        this.name = projects.getName();
        this.description = projects.getDescription();
        this.dateStated = formatDate(projects.getDateStated());
        this.dateEnded = formatDate(projects.getDateEnded());
        this.status = projects.getStatus().name();
        this.totalIssues = projects.getIssueList().size();
        List<ProjectUser> list = (List<ProjectUser>) projects.getProjectUserList();
        this.totalMember = list.size();
        listMember = new ArrayList<>();
        for (ProjectUser p : list) {
            listMember.add(p.getUsers());
        }
        listIssues = new ArrayList<>();
        for (Issues i : projects.getIssueList()) {

            this.listIssues.add(new IssueDTO(i));
        }
        return this;
    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}


