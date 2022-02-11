package com.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePassRequest implements Serializable {
    private String key;
    private String password;
    public String getPassword(){
        return new String(Base64.getDecoder().decode(password.getBytes(StandardCharsets.UTF_8)));
    }
}

