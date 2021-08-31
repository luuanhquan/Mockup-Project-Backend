package com.service;

import com.entity.CustomUserDetails;
import com.entity.Users;
import com.enums.EMAIL_TYPE;
import com.utils.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Transactional
public class EmailService {
    public static String host= "http://localhost:4200/#/forgot-password/";
    public static String FORGOT_SUBJECT = "RECOVERY EMAIL";
    public static String dateFormat = "ddMMyyyyHHmm";
    private static int timeExprired = 1*60*60;
    private static Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Za-z0-9-]+(\\-[A-Za-z0-9])*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9])");
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UsersService usersService;

    @Autowired
    private AES aes;

    private static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public void sendEmail(String email,String subject, String body) {
        //validate email
        if (!validateEmail(email)) {
            System.out.println(email);
            return;
        }
        try {
            System.out.println(body);
            MimeMessage msg = javaMailSender.createMimeMessage();
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            msg.setSubject(subject);
            msg.setFrom("Quan");
            msg.setContent(body,"text/html");
            javaMailSender.send(msg);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (MailException e) {
            e.printStackTrace();
        }
    }

    public String generateSecretKey(int id) {
        String date= new SimpleDateFormat(dateFormat).format(new Date(System.currentTimeMillis()+timeExprired));
        String key = aes.encrypt(date+id);
        return key;
    }

    public void forgotPass(String email) {
        Users user= usersService.findUserByEmail(email);
        if(ObjectUtil.isEmpty(user)) return;
        String key= generateSecretKey(user.getId());
        sendEmail(email, FORGOT_SUBJECT, "<h1>Please follow this link to recover your password:</h1>\n" +
                "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:separate;line-height:100%;\">\n" +
                "  <tr>\n" +
                "    <td align=\"center\" bgcolor=\"#19cca3\" role=\"presentation\" style=\"border:none;border-radius:6px;cursor:auto;padding:11px 20px;background:#19cca3;\" valign=\"middle\">\n" +
                "      <a href=\""+this.host+key+"\" style=\"background:#19cca3;color:#ffffff;font-family:Helvetica, sans-serif;font-size:18px;font-weight:600;line-height:120%;Margin:0;text-decoration:none;text-transform:none;\" target=\"_blank\">\n" +
                "        Click here!\n" +
                "      </a>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "</table>");
    }

    public boolean checkKey(String key){
        try {
            Date validateDate= new SimpleDateFormat(dateFormat).parse(aes.decrypt(key).substring(0,12));
            return validateDate.before(new Date());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
