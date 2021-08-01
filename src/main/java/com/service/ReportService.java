package com.service;

import com.DTO.ReportsDTO;
import com.entity.Division;
import com.entity.Reports;
import com.entity.Users;

import com.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ReportService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    ReportRepository repository;



    public Reports save(Reports s) {
        return repository.save(s);
    }


//    public ReportsDTO save(ReportsDTO reportsDTO) {
//        return repository.save(reportsDTO);
//    }


    public List<Reports> findAll() {
        return repository.findAll();
    }


    public void deleteById(Integer id) {
        repository.deleteById(id);
    }



    public Optional<Reports> findById(Integer id) {
        return repository.findById(id);
    }

    @Autowired
    DivisionUserService divisionUserService;

//    public  void getEmail(Division division){
//        Users a= divisionUserService.findManager(division);
//        Set<String> listEmail = new HashSet<>();
//        listEmail.add(a.getEmail());
//        if(!(listEmail.size()==0)) {
//            String[] array = listEmail.toArray(new String[0]);
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom("phamninh3333@gmail.com");
//            message.setTo(array);
//            message.setSubject("Test");
//            message.setText("Chao ban minh dung day tu chieu");
//            javaMailSender.send(message);
//        }else {
//            System.out.println("deo dc");
//        }
//    }

    public void sendEmail(String email, String subject, String body){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }
}

