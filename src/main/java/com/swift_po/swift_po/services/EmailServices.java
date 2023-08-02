package com.swift_po.swift_po.services;

import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;



@Service
public class EmailServices {


    @Autowired
    // private EmailServices emailService;
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("swiftpurchaseorders@gmail.com");
        message.setSubject(subject);
        message.setText(content);
        javaMailSender.send(message);
    }

}
