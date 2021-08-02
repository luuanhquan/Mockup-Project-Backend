package com.reponse;

import lombok.Data;

@Data
public class UserResponse {
    private String name;
    private String password;
    private String email;
    private String phone;
}
