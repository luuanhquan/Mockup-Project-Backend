package com.entity;

import com.entity.enumFolder.ActiveStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
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
    public ActiveStatus getStatus() {
        return ActiveStatus.valueOf((int) status);
    }

    public void setStatus(ActiveStatus status) {
        this.status = status.value;
    }
    @OneToMany(mappedBy = "project")
    private Collection<DivisionProject> divisionProjectList;
    @OneToMany(mappedBy = "project")
    private Collection<FileProject> fileProjectList;
    @OneToMany(mappedBy = "project")
    private Collection<Issues> issueList;
    @OneToMany(mappedBy = "project")
    private Collection<ProjectUser> projectUserList;
    @OneToMany(mappedBy = "project")
    private Collection<Reports> reportList;

}
