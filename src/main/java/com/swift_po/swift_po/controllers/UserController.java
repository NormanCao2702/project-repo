package com.swift_po.swift_po.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.swift_po.swift_po.models.users;
import com.swift_po.swift_po.models.userRepo;

@Controller
public class UserController {
    @Autowired
    private userRepo userRepo;

    @GetMapping("/")
    public String index(){
        return "users/index";
    }

    @GetMapping("/signup")
    public String getSignUp() {
        return "users/signup";
    }
    

    @PostMapping("users/add")
    public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response, Model model){
        System.out.println("ADD user");
        String newName = newuser.get("username");
        String newPwd = newuser.get("password");
        String newUName = newuser.get("name");
        // Check if email is already in use
        List<users> existingUsers = userRepo.findByName(newName);
        if (!existingUsers.isEmpty()) {
            String error = "Email already in use. Please choose a different email.";
            model.addAttribute("error", error);
            return "users/signup";
        }
        // Password policy validation
        if (!isStrongPassword(newPwd)) {
            model.addAttribute("error", "Password should be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one digit, and one special character.");
            return "users/signup";
        }
        userRepo.save(new users(newName,newPwd,newUName));
        response.setStatus(201);
        return "users/login";
    }
    
    // Helper method to check password strength
    private boolean isStrongPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(regex);
    }
    @GetMapping("/login")
    public String getLogin(Model model, HttpServletRequest request, HttpSession session){
        users user = (users) session.getAttribute("session_user");
        if (user == null){
            return "users/login";
        }
        else {
            model.addAttribute("user",user);
            return "users/form";
        }
    }


    @PostMapping("/login")
    public String login(@RequestParam Map<String,String> formData, Model model, HttpServletRequest request, HttpSession session){
        // processing login
        String name = formData.get("username");
        String pwd = formData.get("password");
        List<users> userlist = userRepo.findByNameAndPassword(name, pwd);
        if (userlist.isEmpty()){
            model.addAttribute("error", "Invalid username or password");
            return "users/login";
        }
        else {
            // success
            users user = userlist.get(0);
            request.getSession().setAttribute("session_user", user);
            model.addAttribute("user", user);
            return "users/form";
        }
    }


    @GetMapping("/logout")
    public String destroySession(HttpServletRequest request){
        request.getSession().invalidate();
        return "/users/login";
    }
}
