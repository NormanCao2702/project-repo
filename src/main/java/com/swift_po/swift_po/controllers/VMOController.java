package com.swift_po.swift_po.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.swift_po.swift_po.models.Request;
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
        return "users/pending"; 
    }

    @GetMapping("/user-forms")
    public String showUserForms(@RequestParam("userId") int userId, Model model) {
        User user = userRepo.findById(userId).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
            return "users/vmoFormreview"; 
        }
        return "error"; 
    }

    @GetMapping("/VMOviewForm/{rid}")
    public String viewFormDetails(@PathVariable int rid, Model model) {
        Request form = requestRepo.findById(rid).get(0);

        model.addAttribute("form", form);

        return "users/VMOform_details";
    }

}
