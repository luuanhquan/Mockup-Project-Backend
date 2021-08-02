package com.entity;

import com.entity.enums.ACTIVE_STATUS;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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
    @Column(name = "NAME")
    private String name;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DATE_STATED")
    private Date dateStated;
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
    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY)
    private Collection<DivisionProject> divisionProjectList;
    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY)
    private Collection<FileProject> fileProjectList;
    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY)
    private Collection<Issues> issueList;
    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY)
    private Collection<ProjectUser> projectUserList;
    @OneToMany(mappedBy = "project",fetch = FetchType.LAZY)
    private Collection<Reports> reportList;

}
