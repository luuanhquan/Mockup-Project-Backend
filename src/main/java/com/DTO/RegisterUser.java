package com.DTO;

import lombok.Data;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterUser {

    @NotBlank
    @Size(min = 5, max = 52)
    private String username;

    @NotBlank
    @Size(min = 3, max = 52)
    private String email;

    @NotBlank
    @Size(min = 6, max = 52)
    private String password;
    @NotBlank
    @Size(min = 6, max = 52)
    private String confirmPassword;

    @NotBlank
    @Size(min = 10, max = 52)
    private String phone;
}
