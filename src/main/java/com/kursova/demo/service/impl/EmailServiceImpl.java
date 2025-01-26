package com.kursova.demo.service.impl;

import com.kursova.demo.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class EmailServiceImpl implements EmailService {

    private final TemplateEngine templateEngine;

    private final JavaMailSender javaMailSender;
    private final String companyMail;

    public EmailServiceImpl(TemplateEngine templateEngine, JavaMailSender javaMailSender, @Value("${mail.drivetime}") String companyMail) {
        this.templateEngine  = templateEngine;
        this.javaMailSender = javaMailSender;
        this.companyMail = companyMail;
    }

    @Override
    public void sendRegistrationEmail(String userEmail, String userName,String activationCode) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try{
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setFrom(companyMail);
            mimeMessageHelper.setReplyTo(companyMail);
            mimeMessageHelper.setSubject("Welcome to driveTime");
            mimeMessageHelper.setText(generatingRegistrationEmailBody(userName,activationCode),true);

            javaMailSender.send(mimeMessage);
        }catch(MessagingException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendActivatedAccount(String userEmail, String username) {
        MimeMessage mimeMessager = javaMailSender.createMimeMessage();

        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessager);
        try{
            mimeMessageHelper.setTo(userEmail);
            mimeMessageHelper.setFrom(companyMail);
            mimeMessageHelper.setReplyTo(companyMail);
            mimeMessageHelper.setSubject("Welcome to driveTime");
            mimeMessageHelper.setText(generatingSuccessfullyActivationEmailBody(username),true);

            javaMailSender.send(mimeMessager);
        }catch(MessagingException e){
            throw new RuntimeException(e);
        }
    }


    private String generatingRegistrationEmailBody(String userName,String activationCode){

        Context context = new Context();
        context.setVariable("username",userName);
        context.setVariable("activationcde",activationCode);
        return templateEngine.process("email/registration-email.html",context);
    }
    private String generatingSuccessfullyActivationEmailBody(String userName){
        Context context = new Context();
        context.setVariable("username",userName);
        return templateEngine.process("email/welcome",context);
    }
}
