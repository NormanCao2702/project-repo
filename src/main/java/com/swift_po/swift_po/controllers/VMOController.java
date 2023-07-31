package com.swift_po.swift_po.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.swift_po.swift_po.models.RequestReposiory;
import com.swift_po.swift_po.models.User;
import com.swift_po.swift_po.models.userRepo;
import org.springframework.ui.Model;

@Controller
public class VMOController {
    
    @Autowired
    private RequestReposiory requestRepo;

    @Autowired
    private userRepo userRepo;

    @GetMapping("/viewForms")
    public String viewForms(Model model) {
        List<User> usersWithForms = userRepo.findByUserType("IS User");
        model.addAttribute("usersWithForms", usersWithForms);
        return "users/pending"; // Return the Thymeleaf template view name
    }

}
