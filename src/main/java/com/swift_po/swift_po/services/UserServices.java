package com.swift_po.swift_po.services;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.swift_po.swift_po.models.User;
// import com.swift_po.swift_po.models.userRepo;

@Service
public class UserServices {
    @Autowired
    private EmailServices emailService;
    private final BCryptPasswordEncoder passwordEncoder;
    

    public void registerUser(User user) {
        // Send registration email to the user
        String to = user.getEmail();
        String subject = " Swift Purchase Orders!";
        String content = "Dear " + user.getName() + ",\n\nWelcome to Swift Purchase Orders!";
        emailService.sendEmail(to, subject, content);
    }

    public UserServices(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String cryptpass( String password) {
        // Hash password 
        String hashedPassword = passwordEncoder.encode(password);
        return hashedPassword;
        
    }

    public boolean logincryptpassmatch(String loginpass, String storedHashedPassword){
        // Compare the provided password with the hashed password
        return passwordEncoder.matches(loginpass, storedHashedPassword);
    }

    
}


