package com.swift_po.swift_po.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swift_po.swift_po.models.User;

@Service
public class UserServices {
    @Autowired
    private EmailServices emailService;

    public void registerUser(User user) {

        // Send registration email to the user
        String to = user.getEmail();
        String subject = " Swift Purchase Orders!";
        String content = "Dear " + user.getName() + ",\n\nWelcome to Swift Purchase Orders!";
        emailService.sendEmail(to, subject, content);
    }
}
