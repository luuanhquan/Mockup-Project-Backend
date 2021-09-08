package com.DTO;

import com.entity.Users;
import com.enums.ACTIVE_STATUS;
import com.enums.USER_ROLE;
import com.enums.USER_TYPE;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
@Data
public class UserDTOE {
    private Integer id;
    private String role;
    private String username;
//    private String fullname;
    private boolean gender;
    private String type;
    private String phone;
    private String email;
    private String status;
    private String password;
    private String firstName;
    private String middleName;
    private String lastname;
    private Long personalid;
    private String hometown;
    private String education;
    private String school;
    private String major;
    private String avatar;
    private Long dayOffLastYear;
    private String dateCreated;
    private String birthday;

    public UserDTOE(Integer id, Integer role, String username, boolean gender, Long type, String phone, String email, Long status, String password, String firstname, String middlename, String lastname, Long personalid, String hometown, String education, String school, String major, String avatar, Long dayOffLastYear, Date dateCreated, Date birthday) {
        this.id = id;
        this.role = USER_ROLE.valueOf(role).name();
        this.username = username;
//        this.fullname=firstname+ " "+lastname;
        this.gender = gender;
        this.type = USER_TYPE.valueOf(type.intValue()).name();
        this.phone = phone;
        this.email = email;
        this.status = ACTIVE_STATUS.valueOf(status.intValue()).name();
        this.password=password;
        this.lastname = lastname;
        this.personalid = personalid;
        this.hometown = hometown;
        this.education = education;
        this.school = school;
        this.major = major;
        this.avatar = avatar;
        this.dayOffLastYear = dayOffLastYear;
        this.dateCreated = formatDate(dateCreated);
        this.birthday = formatDate(birthday);

    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("dd/MM/yyyy").format(date);
    }

}
