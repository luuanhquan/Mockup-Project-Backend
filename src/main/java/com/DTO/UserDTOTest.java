package com.DTO;

import com.entity.Users;
import com.enums.ACTIVE_STATUS;
import com.enums.USER_ROLE;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;
@Data
public class UserDTOTest {


        private Integer id;
        private String role;
        private String username;
        private String fullname;
        private boolean gender;
        private String type;
        private String phone;
        private String email;
        private String status;




    public UserDTOTest loadFromEntity(Users users) {
            this.fullname=users.getLastname()+" "+users.getFirstname();
            this.id=users.getId();
            this.role=users.getRole().name();
            this.username=users.getFirstname();
            this.type = users.getType().name();
            this.status= users.getStatus().name();
            this.phone = users.getPhone();
            this.email = users.getEmail();
            this.gender=users.isGender();
//        this.gender=users.getGener();

            return this;

        }

        private String formatDate(Date date) {
            return new SimpleDateFormat("dd/MM/yyyy").format(date);
        }

    }

