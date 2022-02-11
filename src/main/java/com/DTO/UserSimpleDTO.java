package com.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSimpleDTO {
    int id;
    String userName;
    String email;
    String phone;
    int role;
}
