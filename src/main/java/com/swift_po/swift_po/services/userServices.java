package com.swift_po.swift_po.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swift_po.swift_po.models.users;


@Service
public class userServices {
    @Autowired
    private emailServices emailService;

    public void registerUser(users User) {

        // Send registration email to the user
        String to = User.getemail();
        String subject = " Swift Purchase Orders!";
        String content = "Dear " + User.getfName() + " "+ User. getlName() + ",\n\nWelcome to Swift Purchase Orders!";
        emailService.sendEmail(to, subject, content);
    }
}
