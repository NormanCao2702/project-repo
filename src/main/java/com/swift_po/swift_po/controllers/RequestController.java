package com.swift_po.swift_po.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @GetMapping("/edit/{rid}")
	public String editEmployeeById(Model model, @PathVariable("rid") int rid, HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        if (user == null)   {
            return "/users/login";
        }
        else {
            model.addAttribute("user", user);
        }
		System.out.println("editEmployeeById" + rid);
		
			List<Request> request = requestRepo.findById(rid);
			model.addAttribute("request", request.get(0));
		return "users/editform";
	}

    @PostMapping("/requests/{rid}")
    public String saveUpdatedrequest(@PathVariable int rid, @ModelAttribute("request") Request request, Model model, HttpSession session) {
        Request request2 = requestRepo.findById(rid).get(0);

        request2.setRid(rid);
        request2.setCompanyCode(request.getCompanyCode());
        request2.setRequestor(request.getRequestor());
        request2.setConsultant(request.getConsultant());
        request2.setDate(request.getDate());
        request2.setEmail(request.getEmail());
        request2.setProjectName(request.getProjectName());
        request2.setCostElement(request.getCostElement());
        request2.setStatementOfWork(request.getStatementOfWork());
        request2.setTotalCost(request.getTotalCost());
        request2.setSourcingJustification(request.getSourcingJustification());
        request2.setStatus(request.getStatus());

        User user = (User) session.getAttribute("session_user");
        if (user == null)   {
            return "/users/login";
        }
        else {
            model.addAttribute("user", user);
        }
        int newUserID = user.getId();
        
        request2.setUserID(newUserID);
        requestRepo.save(request2);

        return "redirect:/form";
    }
}
