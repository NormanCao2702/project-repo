package com.swift_po.swift_po.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.swift_po.swift_po.models.User;
import com.swift_po.swift_po.models.userRepo;
import com.swift_po.swift_po.services.UserServices;

@Controller
public class UserController {
    private static final Logger LOGGER = Logger.getLogger(UserController.class.getName());
    @Autowired
    private userRepo userRepo;
    @Autowired
    private UserServices UserServices;

    @Autowired
    // private BCryptPasswordEncoder encoder;

    @GetMapping("/")
    public String index() {
        return "users/index";
    }

    @GetMapping("/signup")
    public String getSignUp() {
        return "users/signup";
    }

    @GetMapping("/pr")
    public String getPr() {
        return "users/pr";
    }

    @RequestMapping("/src")
    public String getSrc() {
        return "users/srcJustification";
    }

    @PostMapping("/users/add")
    public String addUser(@RequestParam Map<String, String> newuser, HttpServletResponse response, Model model) {
        System.out.println("ADD user");

        String newEmail = newuser.get("email");
        String newName = newuser.get("name");
        ;
        String newPwd = newuser.get("password");
        String newuserType = newuser.get("userType");
        String newCryptedPass = UserServices.cryptpass(newPwd);

        // Check if email is already in use
        List<User> existingUsers = userRepo.findByEmail(newEmail);
        if (newPwd == null || !existingUsers.isEmpty()) {
            String error = "Email already in use. Please choose a different email.";
            model.addAttribute("error", error);
            return "users/signup";
        }
        // Password policy validation
        if (!isStrongPassword(newPwd)) {
            model.addAttribute("error",
                    "Password should be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one digit, and one special character.");
            return "users/signup";
        }
        String avatarImagePath = "/uploads/avatar_images/lightning-logo.png";
        User newUser = new User(newEmail, newName, newCryptedPass, newuserType, avatarImagePath);

        // email new user
        UserServices.registerUser(newUser);// call method from user services to send email.
        userRepo.save(newUser);

        response.setStatus(201);

        System.out.println("User type:" + newuserType);
        return "users/login";
    }

    // Helper method to check password strength
    private boolean isStrongPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password.matches(regex);
    }

    @GetMapping("/login")
    public String getLogin(Model model, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("session_user");

        if (user == null) {
            return "users/login";
        } else {
            model.addAttribute("user", user);
            if ("VMO User".equals(user.getUserType())) {
                // VMO user, redirect to the VMO user dashboard page
                return "users/vmoUser";
            } else if ("IS User".equals(user.getUserType())) {
                // Regular user, redirect to the regular user dashboard page
                return "users/form";
            }
        }
        return null;
    }

    @GetMapping("/form")
    public String getForm(Model model, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        if (user == null) {
            return "users/login";
        } else {
            model.addAttribute("user", user);
            return "users/form";
        }
    }

    @GetMapping("/profile/pr/{id}")
    public String getProfile(Model model, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        if (user == null) {
            return "users/login";
        } else {
            model.addAttribute("user", user);
            return "users/profile";
        }
    }
    
    @PostMapping("/profile/pr/{id}")
    public String updateUserProfile(@PathVariable("id") Integer userId,
            @RequestParam Map<String, String> updatedUserData, HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("session_user");

        if (currentUser == null) {
            return "users/login";
        } else {
            int currentuserid = currentUser.getId();

            if (currentUser == null || (currentuserid != userId)) {
                // If the user is not logged in or trying to update another user's profile,
                // redirect to the login page or handle the case appropriately.
                return "redirect:/login";
            }

            // Extract the updated information from the form data
            String newName = updatedUserData.get("name");
            String newEmail = updatedUserData.get("email");

            // Check if the email is already in use by another user (excluding the current
            // user)
            List<User> existingUsers = userRepo.findByEmail(newEmail);
            if (!existingUsers.isEmpty()) {
                String error = "Email already in use. Please choose a different email.";
                model.addAttribute("error", error);
                return "users/profile";
            }

            // Update the user's information
            currentUser.setName(newName);
            currentUser.setEmail(newEmail);

            // Save the updated user in the repository
            userRepo.save(currentUser);

            // Redirect back to the profile page with the updated user information
            return "redirect:/profile/pr/" + currentUser.getId();
        }

    }

    @PostMapping("/profile/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer userId, @RequestParam Map<String, String> updatedUserData,
            HttpSession session) {
        User currentUser = (User) session.getAttribute("session_user");
        if (currentUser == null) {
            return "login";
        } else {
            int currentuserid = currentUser.getId();

            if (currentUser == null || (currentuserid != userId)) {
                // If the user is not logged in or trying to update another user's profile,
                // redirect to the login page or handle the case appropriately.
                return "login";
            }
            Optional<User> UserOp = userRepo.findById(userId);
            if (UserOp.isPresent()) {
                userRepo.deleteById(userId);
                return "redirect:/logout";
            } else {
                session.invalidate();
                return "redirect:/logout";
            }
        }

    }

    @GetMapping("/forgotpassword")
    public String forgotpassword() {

        return "users/forgotpassword";
    }

    @PostMapping("/forgotpassword")
    public String forgotpassword(@RequestParam("email") String email, Model model) {
        // Check if the email exists in the database or user repository
        List<User> userlist = userRepo.findByEmail(email);
        if (userlist.isEmpty()) {
            // If the email does not exist, show an error message on the same page
            model.addAttribute("error", "Email does not exist.");
            return "users/forgotpassword";
        } else {
            User newuser = userlist.get(0);

            // Generate a password reset token (UUID) and save it in the user's account
            String resetToken = UUID.randomUUID().toString();
            newuser.setPasswordResetToken(resetToken);
            userRepo.save(newuser);

            // email user with reset token
            UserServices.resetpassword(newuser);

            // Show a success message on the same page or redirect to a confirmation page
            model.addAttribute("success", "Password reset link has been sent to your email.");
            return "users/login";
        }
    }

    @GetMapping("/resetpassword")
    public String showResetPasswordPage(@RequestParam("token") String token, Model model) {
        model.addAttribute("token", token);
        return "users/resetpassword";
    }

    @PostMapping("/resetpassword")
    public String resetPassword(@RequestParam("token") String token, @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword, Model model) {
        // Find the user by the reset token
        List<User> userlist = userRepo.findByPasswordResetToken(token);
        User currentuser = userlist.get(0);

        if (currentuser == null) {
            // Invalid or expired token
            model.addAttribute("error", "Invalid or expired token.");
            return "users/resetpassword";
        }

        if (!password.equals(confirmPassword)) {
            // Password and confirm password don't match
            model.addAttribute("error", "Password and confirm password do not match.");
            model.addAttribute("token", token); // Pass the token back to the form
            return "users/resetpassword";
        }

        // Update the user's password and clear the reset token
        String newCryptedPass = UserServices.cryptpass(password);
        currentuser.setPassword(newCryptedPass);
        currentuser.setPasswordResetToken(null);
        userRepo.save(currentuser);

        // Show a success message or redirect to the login page
        model.addAttribute("success", "Password has been reset successfully. Please login with your new password.");
        return "users/login";
    }

    @GetMapping("/form/pr/{id}")
    public String showForm(Model model, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        if (user == null) {
            return "users/login";
        } else {
            model.addAttribute("user", user);
            try {
                List<String> firstColumnData = readOptionsFromFile("options.txt");
                model.addAttribute("firstColumnData", firstColumnData);
            } catch (IOException e) {
                // Handle the exception appropriately
                System.err.println("Error reading options from file: " + e.getMessage());
                e.printStackTrace();
                return "error"; // or some other error template
            }	
            LOGGER.info("Exiting vendorList method.");	
            return "users/formpr";
        }
    }	
    
    // Method to read data from the text file	
    private List<String> readOptionsFromFile(String fileName) throws IOException {	
        List<String> options = new ArrayList<>();	
        
        // Load the file using ClassLoader	
        ClassLoader classLoader = getClass().getClassLoader();	
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName);	
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {	
        
            String line;	
            while ((line = reader.readLine()) != null) {	
                options.add(line.trim());	
            }	
        }	
        return options;
    }

    @GetMapping("/pr/{id}")
    public String getPr(Model model, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        if (user == null) {
            return "users/login";
        } else {
            model.addAttribute("user", user);
            return "users/pr";
        }
    }

    @PostMapping("/src/{id}")
    public String getSrc(Model model, HttpServletRequest request, HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        if (user == null) {
            return "users/login";
        } else {
            model.addAttribute("user", user);
            return "users/srcJustification";
        }
    }

    @PostMapping("/login")
    public String login(@RequestParam Map<String, String> formData, Model model, HttpServletRequest request,
            HttpSession session) {
        // processing login
        String uname = formData.get("username");
        String pwd = formData.get("password");

        List<User> userlist = userRepo.findByEmail(uname);
        if (userlist.isEmpty()) {
            model.addAttribute("error", "Invalid username or password");
            return "users/login";
        } else {
            // success
            User user = userlist.get(0);

            String type = user.getUserType();
            // get user pass
            String storedHashPass = user.getPassword();
            // check is they match

            if (UserServices.logincryptpassmatch(pwd, storedHashPass)) {
                // if they match then login
                request.getSession().setAttribute("session_user", user);
                model.addAttribute("user", user);
                if ("VMO User".equals(type)) {
                    return "users/vmoUser";
                } else if ("IS User".equals(type)) {
                    return "users/form";
                }
            } else {
                // if they do not match then giev them prompt saying that it does match
                model.addAttribute("error", "Invalid Username or Password");
                return "users/login";
            }
        }
        return null;
    }

    @GetMapping("/logout")
    public String destroySession(HttpServletRequest request) {
        request.getSession().invalidate();
        return "users/login";
    }

}
