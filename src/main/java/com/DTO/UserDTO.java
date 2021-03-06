package com.DTO;


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

    private String firstname;
    private String middlename;
    private String lastname;
    private String avatar;
    private String password;
    private Boolean gender;
    private String type;
    private String birthday;
    private String phone;
    private String email;
    private String hometown;
    private long personalid;
    private String education;
    private String school;
    private String major;
    private String name;   //division name

    public UserDTO loadFromEntity(Users users) {


        this.firstname = users.getFirstname();
        this.middlename = users.getMiddlename();
        this.lastname = users.getLastname();
        this.avatar = users.getAvatar();
        this.password = users.getPassword();
        this.type = users.getType().name();
        this.birthday = formatDate(users.getBirthday());
        this.phone = users.getPhone();
        this.email = users.getEmail();
        this.gender=users.isGender();
        this.hometown = users.getHometown();
        this.personalid = users.getPersonalid();
        this.education = users.getEducation();
        this.school = users.getSchool();
        this.major = users.getMajor();
//

        return this;

    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

}
