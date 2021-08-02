package com.service;

import com.entity.Users;
import com.enums.EMAIL_TYPE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    private static Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("\\b[A-Z0-9._%-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}\\b");

    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public static String FORGOT_SUBJECT = "RECOVERY EMAIL";
    public static String FORGOT_BODY = "Please follow this link to recover your password:\n";

    public void sendEmail(String email, EMAIL_TYPE type) {
        //validate email
        if (!validateEmail(email)) return;
        SimpleMailMessage msg = new SimpleMailMessage();
        switch (type) {
            case FORGOT: {
                msg.setTo(email);
                msg.setSubject(FORGOT_SUBJECT);
                msg.setText(FORGOT_BODY);
            }
            case ACTIVE: {

            }
        }

        javaMailSender.send(msg);
    }

    public String generateSecretKey(Users users) {
        return null;
    }
}
