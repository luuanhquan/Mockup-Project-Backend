package com.controllers;

import com.DTO.AuthenticationBean;
import com.DTO.UserDTO;
import com.DTO.UserDTOTest;
import com.entity.CustomUserDetails;
import com.entity.Users;
import com.enums.ACTIVE_STATUS;
import com.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private JavaMailSender sender;
    @Autowired
    public UsersService usersService;


    @GetMapping(path = "/auth")
    public AuthenticationBean basicauth() {
        CustomUserDetails auth = (CustomUserDetails)
                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return new AuthenticationBean(auth.getUser());
    }

    @GetMapping("/all")
    public List<UserDTOTest> getAllEmployees() {

        List<Users> users = usersService.findAll();
        List<UserDTOTest> usersDTOS = new ArrayList<>();
        for (Users u : users) {
            UserDTOTest DTO = new UserDTOTest();
            DTO.loadFromEntity(u);
            usersDTOS.add(DTO);
        }
        return usersDTOS;
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<Users> getEmployeeById(@PathVariable("id") Integer id) {
        Users users = usersService.findById(id).orElse(null);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Users> addEmployee(@RequestBody Users users) {

        System.out.println(users);
        Users newEmployee = usersService.save(users);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);


    }
    public void test(){
         System.out.println("aaaa");

    }

//Send mail
//    public String sendMail() {
//        MimeMessage message = sender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(message);
//
//        try {
//            helper.setTo("phambachd30@gmail.com");
//            helper.setText("Greetings :)");
//            helper.setSubject("Mail From Spring Boot");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//            return "Error while sending mail ..";
//        }
//        sender.send(message);
//        return "Mail Sent Success!";
//    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Users> updateEmployee(@RequestBody Users users, @PathVariable("id") int id) {
        users.setId(id);
        System.out.println(users);
        Users updateEmployee = usersService.save(users);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }
    // edit
//    @PutMapping("/move/{id}")
//    public ResponseEntity<Userss> moveEmployee(@RequestBody Userss userss, @PathVariable("id")int id) {
//        userss.setId(id);
//        System.out.println(userss);
//        Userss moveEmployee = userService.moveEmployee(userss);
//        return new ResponseEntity<>(moveEmployee, HttpStatus.OK);
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Integer id) {
        Users user = usersService.findById(id).orElse(null);
        user.setStatus(ACTIVE_STATUS.INACTIVE);
        usersService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}



