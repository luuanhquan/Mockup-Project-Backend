package com.controllers;

import com.DTO.ProjectDetailDTO;
import com.DTO.Seach;
import com.DTO.UserDTOE;
import com.entity.CustomUserDetails;
import com.entity.Projects;
import com.entity.Users;
import com.enums.ACTIVE_STATUS;
import com.service.UsersService;
import com.utils.ObjectUtil;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserControler {

    static final int pageSize= 5;

    @Autowired
    private JavaMailSender sender;
    @Autowired
    public UsersService usersService;


//    @GetMapping(path = "/auth")
//    public AuthenticationBean basicauth() {
//        CustomUserDetails auth = (CustomUserDetails)
//                SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        return new AuthenticationBean(auth.getUser());
//    }

    @GetMapping("/all/{pageNumber}")
    public ResponseEntity getAllEmployees(@PathVariable int pageNumber) {
        List<UserDTOE> userDTOE=usersService.findAllDTO(pageSize,pageNumber).toList();
         return new ResponseEntity(userDTOE,HttpStatus.OK);

    }
    @GetMapping("/alls")
    public ResponseEntity getAllEmployees() {
        List<UserDTOE> userDTOE=usersService.findAlls();
        return new ResponseEntity(userDTOE,HttpStatus.OK);
    }


    @GetMapping("/find/{id}")
    public ResponseEntity  getUserById(@PathVariable("id") Integer id) {
        UserDTOE userDTOE=usersService.findUserById(id);
        return new ResponseEntity<>(userDTOE, HttpStatus.OK);

    }


    @PostMapping("/search")
    public ResponseEntity Search(@RequestBody Seach seach)  {
        int seachByName ;
        String seachByRole = "";
        long seachByStatus;
        seachByName = Integer.valueOf(seach.getKeyword1());
        seachByRole = seach.getKeyword2();
        seachByStatus=Long.valueOf(seach.getKeyword3());
        System.out.println("ZZZZZZZZZZZZZZZZZ");
        List<UserDTOE> SeachName = usersService.search(seachByRole,seachByName,seachByStatus);
        return new ResponseEntity<>(SeachName, HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody UserDTOE userDTOE) throws ParseException {
        System.out.println(userDTOE);
        Users users=new Users().loadFromDTOE(userDTOE);
        usersService.addUser(users);
        return new ResponseEntity(users, HttpStatus.CREATED);

    }
//    public void test(){
//        System.out.println("aaaa");
//
//    }

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


    @PutMapping(value = "/update/{id}", produces = "application/json")
    public ResponseEntity updateEmployee(@RequestBody UserDTOE userDTOE, @PathVariable("id") int id) throws ParseException {
       Users users=usersService.findById(id);
       usersService.updateUser(users.loadFromDTOE(userDTOE));
       return new ResponseEntity(users,HttpStatus.OK);

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
        Users users= usersService.findById(id);
        users.setStatus(ACTIVE_STATUS.INACTIVE);
        usersService.updateUser(users);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
