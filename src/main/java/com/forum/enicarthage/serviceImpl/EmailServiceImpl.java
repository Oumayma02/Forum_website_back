package com.forum.enicarthage.serviceImpl;
import java.io.File;

import com.forum.enicarthage.model.Email;
import com.forum.enicarthage.services.EmailService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LogManager.getLogger(EmailServiceImpl.class);
    @Autowired
    private  JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;
    @Override
    public String sendemail(Email email) {
       try {
           logger.info("Mail sent Successfully ...");
           SimpleMailMessage mailMessage=new SimpleMailMessage();
           mailMessage.setFrom(sender);
           mailMessage.setTo(email.getRecipient());
           mailMessage.setText(email.getBody());
           mailMessage.setSubject(email.getSubject());
           javaMailSender.send(mailMessage);
           return  "Mail sent Successfully ...";

       }catch (Exception e){
           logger.info("Error while Sending Mail");
           return  "Error while Sending Mail";
       }
    }
}
