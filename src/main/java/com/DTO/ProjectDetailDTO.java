package com.DTO;

import com.entity.Issues;
import com.entity.ProjectUser;
import com.entity.Projects;
import com.entity.Users;
import com.enums.ACTIVE_STATUS;
import lombok.Data;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ProjectDetailDTO {
    private Integer id;
    private String name;
    private String description;
    private Date dateStated;
    private Date dateEnded;
    private String status;
    private List<UserSimpleDTO> listMember;
    private int totalMember;
    private int totalIssues;
    private List<IssueSummaryDTO> listIssues;
    private PM PM;
    private String division;

//    public ProjectDetailDTO loadFromEntity(Projects projects) {
//        this.id = projects.getId();
//        this.name = projects.getName();
//        this.description = projects.getDescription();
//        this.dateStated = formatDate(projects.getDateStated());
//        this.dateEnded = formatDate(projects.getDateEnded());
//        this.status = projects.getStatus().name();
//        this.totalIssues = projects.getIssueList().size();
//        List<ProjectUser> list = (List<ProjectUser>) projects.getProjectUserList();
//        this.totalMember = list.size();
//        listMember = new ArrayList<>();
//        for (ProjectUser p : list) {
//            listMember.add(p.getUsers());
//        }
//        listIssues = new ArrayList<>();
//        for (Issues i : projects.getIssueList()) {
//
//            this.listIssues.add(new IssueSummaryDTO(i));
//        }
//        return this;
//    }

    public ProjectDetailDTO(Integer id, String name, String description, Date dateStated, Date dateEnded, int status, int totalIssues, int totalMember) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateStated = dateStated;
        this.dateEnded = dateEnded;
        this.status = ACTIVE_STATUS.valueOf(status).toString();
        this.totalIssues=totalIssues;
        this.totalMember=totalMember;
        this.division=division;

    }
    private String formatDate(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }
}


