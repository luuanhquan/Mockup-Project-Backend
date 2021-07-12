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
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "ROLE")
    private long role;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Column(name = "MIDDLENAME")
    private String middlename;
    @Column(name = "LASTNAME")
    private String lastname;
    @Column(name = "TYPE")
    private long type;
    @Column(name = "PERSONALID")
    private Long personalid;
    @Column(name = "HOMETOWN")
    private String hometown;
    @Column(name = "EDUCATION")
    private String education;
    @Column(name = "SCHOOL")
    private String school;
    @Column(name = "MAJOR")
    private String major;
    @Column(name = "STATUS")
    private long status;
    public ActiveStatus getStatus() {
        return ActiveStatus.valueOf((int) status);
    }

    public void setStatus(ActiveStatus status) {
        this.status = status.value;
    }
    @Column(name = "DAY_OFF_LAST_YEAR")
    private Long dayOffLastYear;
    @Column(name = "DATE_CREATED")
    private Date dateCreated;
    @Column(name = "GENDER")
    private boolean gender;
    @Column(name = "BIRTHDAY")
    private Date birthday;
    @OneToMany(mappedBy = "users")
    private Collection<Division> divisionList;
    @OneToMany(mappedBy = "uploader")
    private Collection<Files> fileList;
    @OneToMany(mappedBy = "userCreated")
    private Collection<Issues> issuesByCreator;
    @OneToMany(mappedBy = "assignee")
    private Collection<Issues> issuesByAssignee;
    @OneToMany(mappedBy = "users")
    private Collection<IssueChangeLog> issueChangeLogsList;
    @OneToMany(mappedBy = "userRequested")
    private Collection<LeaveRequests> leaveRequestsByRequester;
    @OneToMany(mappedBy = "userApproved")
    private Collection<LeaveRequests> leaveRequestsByApprover;
    @OneToMany(mappedBy = "users")
    private Collection<ProjectUser> projectUsersList;
    @OneToMany(mappedBy = "users")
    private Collection<Reports> reportsList;
    @OneToMany(mappedBy = "users")
    private Collection<TimeLog> timeLogsList;
    @ManyToOne
    @JoinColumn(name = "DIVISIONID", referencedColumnName = "ID", nullable = false)
    private Division division;
    @ManyToOne
    @JoinColumn(name = "AVATAR", referencedColumnName = "ID")
    private Files avatar;

}
