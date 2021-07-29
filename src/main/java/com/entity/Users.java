package com.entity;

import com.entity.enums.ACTIVE_STATUS;
import com.entity.enums.USER_ROLE;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ROLE")
    private int role;

    public Users() {

    }

    public String getRole() {
        return USER_ROLE.valueOf(role).name();
    }

    public void setRole(USER_ROLE role) {
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
    @OneToMany(mappedBy = "uploader",fetch = FetchType.LAZY)
    private Collection<Files> fileList;
    @OneToMany(mappedBy = "userCreated",fetch = FetchType.LAZY)
    private Collection<Issues> issuesByCreator;
    @OneToMany(mappedBy = "assignee",fetch = FetchType.LAZY)
    private Collection<Issues> issuesByAssignee;
    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY)
    private Collection<IssueChangeLog> issueChangeLogsList;
    @OneToMany(mappedBy = "userRequested",fetch = FetchType.LAZY)
    private Collection<LeaveRequests> leaveRequestsByRequester;
    @OneToMany(mappedBy = "userApproved",fetch = FetchType.LAZY)
    private Collection<LeaveRequests> leaveRequestsByApprover;
    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY)
    private Collection<ProjectUser> projectUsersList;
    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY)
    private Collection<Reports> reportsList;
    @OneToMany(mappedBy = "users",fetch = FetchType.LAZY)
    private Collection<TimeLog> timeLogsList;
    @Column(name = "Avatar")
    private String avatar;

}
