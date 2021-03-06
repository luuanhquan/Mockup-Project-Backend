package com.service;

import com.entity.Reports;
import com.repositories.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    ReportRepository repository;
    @Autowired
    DivisionUserService divisionUserService;
    @Autowired
    private JavaMailSender javaMailSender;


//    public ReportsDTO save(ReportsDTO reportsDTO) {
//        return repository.save(reportsDTO);
//    }

    public Reports save(Reports s) {
        return (Reports) repository.findAll();
    }

    public List<Reports> findAll() {
        return repository.findAll();
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public Optional<Reports> findById(Integer id) {
        return repository.findById(id);
    }

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

    public void sendEmail(String email, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(email);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }
}

