package com.swift_po.swift_po.controllers;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.swift_po.swift_po.models.Request;
import com.swift_po.swift_po.models.RequestReposiory;
import com.swift_po.swift_po.models.User;
import com.swift_po.swift_po.models.userRepo;
import com.swift_po.swift_po.services.UserServices;

import jakarta.servlet.http.HttpSession;

@Controller
public class VMOController {

    @Autowired
    private RequestReposiory requestRepo;
    @Autowired
    private UserServices UserServices;
    @Autowired
    private userRepo userRepo;

    @GetMapping("/viewForms")
    public String viewForms(Model model) {
        List<User> usersWithForms = userRepo.findByUserType("IS User");
        // Create an iterator to safely remove elements from the list
        Iterator<User> iterator = usersWithForms.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            List<Request> requests = requestRepo.findByUserID(user.getId());
            if (requests.size() == 0) {
                iterator.remove();
            }
        }
        boolean hasSubmitted = false;
        while (iterator.hasNext()) {
            User user = iterator.next();
            List<Request> requests = requestRepo.findByUserID(user.getId());
            for (Request request : requests) {
                if (request.getStatus().equals("Submitted")) {
                    hasSubmitted = true;
                    break;
                }
            }
            if (!hasSubmitted) {
                iterator.remove();
            }
        }
        model.addAttribute("usersWithForms", usersWithForms);
        return "users/pending";
    }

    @GetMapping("/user-forms/{id}")
    public String showUserForms(@PathVariable("id") int userId, Model model) {
        List<Request> requests = requestRepo.findAll();
        List<User> Luser = userRepo.findById(userId);
        User user = Luser.get(0);
        if (user != null) {
            model.addAttribute("requests", requests);
            model.addAttribute("user", user);
            return "users/vmoFormreview";
        }
        return "error";
    }

    @GetMapping("/VMOviewForm/{rid}")
    public String viewFormDetails(@PathVariable int rid, Model model) {
        Request request = requestRepo.findById(rid).get(0);

        model.addAttribute("request", request);

        return "users/VMOform_details";
    }

    @PostMapping("/vmoRequests/{rid}")
    public String saveUpdatedrequest(@PathVariable int rid, @ModelAttribute("request") Request request, Model model,
            HttpSession session) {
        System.out.println("saveUpdatedrequest" + rid);
        Request request2 = requestRepo.findById(rid).get(0);
        request2.setStatus(request.getStatus());

        User user = (User) session.getAttribute("session_user");
        if (user == null) {
            return "users/login";
        } else {
            model.addAttribute("user", user);
        }
        int newUserID = user.getId();

        int newISUser = request2.getUserID();

        String newComments = request.getComments();
        request2.setComments(newComments);

        request2.setUserID(newUserID);
        requestRepo.save(request2);

        List<User> tempt = userRepo.findById(newISUser);
        User temptuser = tempt.get(0);

        if (request2.getStatus().equals("approved"))
            UserServices.vmoApprovalnotification(temptuser, request2);
        else {
            UserServices.vmoDenialnotification(temptuser, request2);
        }
        return "users/vmoUser";
    }

    @GetMapping("/viewApproval")
    public String viewApprovalForms(Model model) {
        List<Request> approvedForms = requestRepo.findByStatus("approved");
        model.addAttribute("approvedForms", approvedForms);
        return "users/vmoApproval";
    }

    @GetMapping("/viewDenial")
    public String viewDenialForms(Model model) {
        List<Request> deniedForms = requestRepo.findByStatus("rejected");
        model.addAttribute("deniedForms", deniedForms);
        return "users/vmoDenial";
    }
}
