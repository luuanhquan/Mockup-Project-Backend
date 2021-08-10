package com.controllers;

import com.entity.Users;
import com.enums.STATUS_REGISTER;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegisterController {

    @Autowired
    private UsersService usersService;

    @PostMapping(path = "/register")
    public String registerNewUser(@RequestBody Users users) {
        STATUS_REGISTER statusRegister = usersService.registerNewUser(users);
        if (statusRegister.equals(STATUS_REGISTER.Error_OnSystem)) {
            return "redirect:/register?error-system";
        }
        return "redirect:/login";//viết lại
    }

}
