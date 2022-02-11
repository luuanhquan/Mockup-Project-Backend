package com.configs.Cron;

import com.service.EmailService;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Configuration
@EnableScheduling
public class Birthday {
    @Autowired
    EmailService emailService;

    @Autowired
    UsersService usersService;


//    @Scheduled(cron = "0/6 * * * * *")
    @Scheduled(cron = "0 0 8 * * *")
    public void scheduleBirthday(){
        List<String> emailList= usersService.findBirthday();
        emailService.sendBirthdayEmail(emailList);
    }
}
