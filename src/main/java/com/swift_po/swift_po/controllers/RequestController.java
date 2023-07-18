package com.swift_po.swift_po.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.swift_po.swift_po.models.Request;
import com.swift_po.swift_po.models.RequestReposiory;
import com.swift_po.swift_po.models.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class RequestController {
    @Autowired
    private RequestReposiory requestRepo;
    
    @PostMapping("requests/add")
    public String addRequest(@RequestParam Map<String, String> newRequest, HttpServletResponse response, Model model, @RequestParam(value = "status", required = false) String status, HttpServletRequest request, HttpSession session) {
        System.out.println("ADD request");

        // Get user who submitted teh request
        User user = (User) session.getAttribute("session_user");
        if (user == null)   {
            return "/users/login";
        }
        else {
            model.addAttribute("user", user);
        }

        String newCompanyCode = newRequest.get("companyCode");
        String newRequestor = newRequest.get("requestor");
        String newConsultant = newRequest.get("consultant");
        String newDate = newRequest.get("date");
        String newEmail = newRequest.get("email");
        String newProjectName = newRequest.get("projectName");
        String newCostElement = newRequest.get("costElement");
        String newStatementOfWork = newRequest.get("statementOfWork");
        String newTotalCost = newRequest.get("totalCost");
        String newSourcingJustification = newRequest.get("sourcingJustification");
        String newStatus = newRequest.get("status");
        int newUserID = user.getId();


        Request newRequestObj = new Request(newCompanyCode, newRequestor, newConsultant, newDate, newEmail, newProjectName, newCostElement, newStatementOfWork, newTotalCost, newSourcingJustification, newStatus, newUserID);
        requestRepo.save(newRequestObj);
        return "redirect:/form";

    }

    @GetMapping("/requests/draft")
    public String getDrafts(Model model, HttpServletRequest request, HttpSession session) {
        System.out.println("Getting all draft requests");
        //get all requests from database
        User user = (User) session.getAttribute("session_user");
        if (user == null)   {
            return "/users/login";
        }
        else {
            model.addAttribute("user", user);
        }
        List<Request> requests = requestRepo.findAll();
        //end of database call
        model.addAttribute("requests", requests);
        System.out.println("Drafts:");
        return "users/formdrafts";
    }

    @GetMapping("/requests/submitted")
    public String getSubmitted(Model model, HttpServletRequest request, HttpSession session) {
        System.out.println("Getting all submitted requests");
        //get all requests from database
        User user = (User) session.getAttribute("session_user");
        if (user == null)   {
            return "/users/login";
        }
        else {
            model.addAttribute("user", user);
        }
        List<Request> requests = requestRepo.findAll();
        //end of database call
        model.addAttribute("requests", requests);
        return "users/formssubmitted";
    }
}