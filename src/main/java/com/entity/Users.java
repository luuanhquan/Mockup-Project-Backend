package com.entity;

import com.entity.enums.ACTIVE_STATUS;
import com.entity.enums.USERROLE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ROLE")
    private int role;
    public USERROLE getRole() {
        return USERROLE.valueOf(role);
    }

    public void setRole(USERROLE role) {
        this.status = role.value;
    }
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
    public ACTIVE_STATUS getStatus() {
        return ACTIVE_STATUS.valueOf((int) status);
    }

    public void setStatus(ACTIVE_STATUS status) {
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
    @JsonIgnore
    @JoinColumn(name = "DIVISIONID", referencedColumnName = "ID", nullable = false)
    private Division division;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "AVATAR", referencedColumnName = "ID")
    private Files avatar;

}
