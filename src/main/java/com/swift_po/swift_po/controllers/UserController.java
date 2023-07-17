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
import com.swift_po.swift_po.models.User;
import com.swift_po.swift_po.models.userRepo;
import com.swift_po.swift_po.services.UserServices;

@Controller
public class UserController {
    @Autowired
    private userRepo userRepo;
    @Autowired
    private UserServices UserServices;
    

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
    
        String newEmail = newuser.get("email");
        String newFName = newuser.get("fName");
        String newLName = newuser.get("lName");
        String newPwd = newuser.get("password");
        String newuserType = newuser.get("userType");

        // Check if email is already in use
        List<User> existingUsers = userRepo.findByEmail(newEmail);
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
        userRepo.save(new User( newEmail, newFName, newLName, newPwd, newuserType));
        User tempuser = new User( newEmail, newFName, newLName, newPwd, newuserType);
        UserServices.registerUser(tempuser);
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
        User user = (User) session.getAttribute("session_user");
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
        String uname = formData.get("username");
        String pwd = formData.get("password");
        List<User> userlist = userRepo.findByEmailAndPassword(uname, pwd);
        if (userlist.isEmpty()){
            model.addAttribute("error", "Invalid username or password");
            return "users/login";
        }
        else {
            // success
            User user = userlist.get(0);
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



