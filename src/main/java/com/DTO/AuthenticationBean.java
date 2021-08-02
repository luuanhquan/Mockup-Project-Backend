package com.DTO;

import com.entity.Users;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
public class AuthenticationBean {
    private String username;
    private String role;
    private String avatar;

    public AuthenticationBean(Users user_login) {
        this.username = user_login.getUsername();
        this.role= user_login.getRole().substring(5);
        this.avatar= user_login.getAvatar();

    }

}
