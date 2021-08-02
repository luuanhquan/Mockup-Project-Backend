package com.controllers;

import com.entity.Users;
import com.entity.enums.STATUS_REGISTER;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class DefaultController {

    @Autowired
    private UsersService usersService;



    @GetMapping(path="/register")
    public String register(Model model) {
        model.addAttribute("users", new Users());
        return "/register";
    }


    @RequestMapping(path="/register-users", method = RequestMethod.POST)
    public String registerNewUser(@Valid @ModelAttribute("users") Users users){
        STATUS_REGISTER statusRegister = usersService.registerNewUser(users);

        if (statusRegister.equals(STATUS_REGISTER.Error_OnSystem)){
            return "redirect:/register?error-system";
        }
        return "redirect:/login";
    }

}
