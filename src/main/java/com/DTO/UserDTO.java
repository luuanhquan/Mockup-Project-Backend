package com.dto;


import com.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Component
public class UserDTO {
    private String username;
    private String firstname;
    private String middlename;
    private String lastname;
    private String avatar;
    private String password;
    private String gender;
    private String type;
    private String birthday;
    private String phone;
    private String email;
    private String hometown;
    private long personalid;
    private String education;
    private String school;
    private String major;

//    public UserDTO loadFromEntity(Users users) {
//
//        this.username = users.getUsername();
//        this.firstname = users.getFirstname();
//        this.middlename = users.getMiddlename();
//        this.lastname = users.getLastname();
//        this.avatar = users.getAvatar();
//        this.password = users.getPassword();
//        this.type = users.getType().name();
//        this.birthday = formatDate(users.getBirthday());
//        this.phone = users.getPhone();
//        this.email = users.getEmail();
////        this.gender=users.getGener();
//        this.hometown = users.getHometown();
//        this.personalid = users.getPersonalid();
//        this.education = users.getEducation();
//        this.school = users.getSchool();
//        this.major = users.getMajor();
//        return this;
//
//    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

}
