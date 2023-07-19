package com.swift_po.swift_po.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
    @Autowired
    // private BCryptPasswordEncoder encoder;
    

    @GetMapping("/")
    public String index(){
        return "users/index";
    }

    @GetMapping("/signup")
    public String getSignUp() {
        return "users/signup";
    }
    
    @GetMapping("/pr")
    public String getPr(){
        return "users/pr";
    }

    @RequestMapping("/src")
    public String getSrc(){
        return "users/srcJustification";
    }

    @PostMapping("users/add")
    public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response, Model model){
        System.out.println("ADD user");
    
        String newEmail = newuser.get("email");
        String newName = newuser.get("name");;
        String newPwd = newuser.get("password");
        String newuserType = newuser.get("userType");
        String newCryptedPass = UserServices.cryptpass(newPwd);


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
        
        userRepo.save(new User( newEmail, newName, newCryptedPass, newuserType));
        User tempuser = new User( newEmail, newName, newCryptedPass, newuserType);
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

    @GetMapping("/form")
    public String getForm(Model model, HttpServletRequest request, HttpSession session){
        User user = (User) session.getAttribute("session_user");
        if (user == null)   {
            return "users/login";
        }
        else {
            model.addAttribute("user",user);
            return "users/form";
        }
    }

    @GetMapping("/form/pr/{id}")
    public String showForm(Model model, HttpServletRequest request, HttpSession session){
        User user = (User) session.getAttribute("session_user");
        if (user == null)   {
            return "users/login";
        }
        else {
            model.addAttribute("user",user);
            return "users/formpr";
        }
    }

    @GetMapping("/pr/{id}")
    public String getPr(Model model, HttpServletRequest request, HttpSession session){
        User user = (User) session.getAttribute("session_user");
        if (user == null)   {
            return "users/login";
        }
        else {
            model.addAttribute("user",user);
            return "users/pr";
        }
    }

    @PostMapping("/src/{id}")
    public String getSrc(Model model, HttpServletRequest request, HttpSession session){
        User user = (User) session.getAttribute("session_user");
        if (user == null)   {
            return "users/login";
        }
        else {
            model.addAttribute("user",user);
            return "users/srcJustification";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam Map<String,String> formData, Model model, HttpServletRequest request, HttpSession session){
        // processing login
        String uname = formData.get("username");
        String pwd = formData.get("password");
        
        List<User> userlist = userRepo.findByEmail(uname);
        if (userlist.isEmpty()){
            model.addAttribute("error", "Invalid username or password");
            return "users/login";
        }
        else {
            // success
            User user = userlist.get(0);
            //get user pass
            String storedHashPass = user.getPassword();
            //check is they match
            
            if (UserServices.logincryptpassmatch(pwd,storedHashPass)) {
                //if they match then login
                request.getSession().setAttribute("session_user", user);
                model.addAttribute("user", user);
                return "redirect:/form";
                
            } else {
                //if they do not match then giev them prompt saying that it does match
                model.addAttribute("error", "Invalid Username or Password");
                return "users/login";
            }
        }
    }

    @GetMapping("/logout")
    public String destroySession(HttpServletRequest request){
        request.getSession().invalidate();
        return "users/login";
    }
}



