package com.entity;

import com.DTO.ProjectCreateDTO;
import com.DTO.ProjectDTO;
import com.entity.enums.ACTIVE_STATUS;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "PROJECTS")
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    // tên dự án
    @Column(name = "NAME")
    // mô tả dự án
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    // ngày bắt đầu
    @Column(name = "DATE_STATED")
    private Date dateStated;
    // ngày kết thúc
    @Column(name = "DATE_ENDED")
    private Date dateEnded;
    @Column(name = "STATUS")
    private int status;

    public ACTIVE_STATUS getStatus() {
        return ACTIVE_STATUS.valueOf((int) status);
    }

    public void setStatus(ACTIVE_STATUS status) {
        this.status = status.value;
    }

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private Collection<DivisionProject> divisionProjectList;
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private Collection<FileProject> fileProjectList;
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private Collection<Issues> issueList;
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private Collection<ProjectUser> projectUserList;
    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY)
    private Collection<Reports> reportList;

    public Projects loadFromDTO(ProjectDTO dto) throws ParseException {
        this.name = dto.getName();
        this.description = dto.getDescription();
        this.dateStated = this.getDate(dto.getDateStated());
        this.dateEnded = this.getDate(dto.getDateEnded());
        this.status = ACTIVE_STATUS.valueOf(dto.getStatus()).value;
        return this;
    }

    public Projects loadFromDTO(ProjectCreateDTO dto) throws ParseException {
        this.name = dto.getName();
        this.description = dto.getDes();
        this.dateStated = dto.getDate_start();
        this.dateEnded = dto.getDate_end();
        this.status = ACTIVE_STATUS.valueOf(dto.getStatus()).value;
        return this;
    }

    private Date getDate(String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(date);

    }
}
