package com.entity;

import com.DTO.UserDTO;
import com.DTO.UserDTOE;
import com.enums.ACTIVE_STATUS;
import com.enums.USER_ROLE;
import com.enums.USER_TYPE;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    @SequenceGenerator(name = "seq", sequenceName = "USERS_SEQ2", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "ROLE")
    private int role;
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
    @Column(name = "AVATAR")
    private String avatar;
    @Column(name = "DAY_OFF_LAST_YEAR")
    private Long dayOffLastYear;
    @Column(name = "DATE_CREATED")
    private Date dateCreated;
    @Column(name = "GENDER")
    private boolean gender;
    @Column(name = "BIRTHDAY")
    private Date birthday;
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
//
//    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name = "USERID", referencedColumnName = "ID", nullable = false)
//    private DivisionUser divisionUser;



    public USER_ROLE getRole() {
        return USER_ROLE.valueOf(role);
    }

    public void setRole(USER_ROLE role) {
        this.status = role.value;
    }

    public USER_TYPE getType() {
        return USER_TYPE.valueOf((int) type);
    }

    public void setType(USER_TYPE type) {
        this.type = type.value;
    }

    public ACTIVE_STATUS getStatus() {
        return ACTIVE_STATUS.valueOf((int) status);
    }

    public void setStatus(ACTIVE_STATUS status) {
        this.status = status.value;
    }


   public Users loadFromDTO(UserDTO dto) throws ParseException {


       this.password = dto.getPassword();
       this.email = dto.getEmail();
       this.phone = dto.getPhone();
       this.type = USER_TYPE.valueOf(dto.getType()).value;
       this.avatar = dto.getAvatar();
       this.hometown = dto.getHometown();
       this.personalid = dto.getPersonalid();
       this.firstname = dto.getFirstname();
       this.middlename = dto.getMiddlename();
       this.lastname = dto.getLastname();
       this.gender=dto.getGender();
        this.birthday = this.getDate(dto.getBirthday());
        this.education = dto.getEducation();
        this.school = dto.getSchool();
        this.major = dto.getMajor();
        return this;
    }

    public Users loadFromDTOE(UserDTOE dto) throws ParseException {
        this.id=dto.getId();
        this.role=USER_ROLE.valueOf(dto.getRole()).value;
        this.username = dto.getUsername();
        this.gender=dto.isGender();
        this.type = USER_TYPE.valueOf(dto.getType()).value;
        this.phone = dto.getPhone();
        this.email = dto.getEmail();
        this.status=ACTIVE_STATUS.valueOf(dto.getStatus()).value;
        this.password = dto.getPassword();
        this.firstname = dto.getFirstName();
        this.middlename = dto.getMiddleName();
        this.lastname = dto.getLastname();
        this.personalid = dto.getPersonalid();
        this.hometown = dto.getHometown();

        this.education = dto.getEducation();
        this.school = dto.getSchool();
        this.major = dto.getMajor();
        this.avatar = dto.getAvatar();
        this.dayOffLastYear=dto.getDayOffLastYear();
        this.dateCreated=getDate(dto.getDateCreated());
        this.birthday=getDate(dto.getBirthday());
        return this;
    }
    private Date getDate(String date) throws ParseException {
        return new SimpleDateFormat("dd/MM/yyyy").parse(date);
    }


}
