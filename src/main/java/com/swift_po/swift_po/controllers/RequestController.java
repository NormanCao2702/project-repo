package com.swift_po.swift_po.controllers;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

// import java.util.Optional;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.swift_po.swift_po.models.Request;
import com.swift_po.swift_po.models.RequestReposiory;
import com.swift_po.swift_po.models.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class RequestController {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public void PdfController(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Autowired
    private RequestReposiory requestRepo;

    @GetMapping(value = "/download_pdf/{rid}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadPdf(@PathVariable int rid) {
        String sql = "SELECT sj_file FROM requests WHERE rid = ?;";
        return jdbcTemplate.execute(sql, (PreparedStatement ps) -> {
            ps.setLong(1, rid);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    byte[] pdfData = rs.getBytes("sj_file");
                    if (pdfData != null) {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentDispositionFormData("attachment", "sjfile.pdf");
                        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        });
    }

    @GetMapping(value = "/download_pc/{rid}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadpc(@PathVariable int rid) {
        String sql = "SELECT pc_file FROM requests WHERE rid = ?;";
        return jdbcTemplate.execute(sql, (PreparedStatement ps) -> {
            ps.setLong(1, rid);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    byte[] pdfData = rs.getBytes("pc_file");
                    if (pdfData != null) {
                        HttpHeaders headers = new HttpHeaders();
                        headers.setContentDispositionFormData("attachment", "pcFile.pdf");
                        return new ResponseEntity<>(pdfData, headers, HttpStatus.OK);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        });
    }

    @PostMapping("requests/add")
    public String addRequest(@RequestParam Map<String, String> newRequest, HttpServletResponse response, Model model,
            @RequestParam(value = "status", required = false) String status, HttpServletRequest request,
            HttpSession session, @RequestParam("sjFile") MultipartFile sjFile,
            @RequestParam("pcFile") MultipartFile pcFile)
            throws IOException {
        System.out.println("===========================ADD request============================");

        // Get user who submitted teh request
        User user = (User) session.getAttribute("session_user");
        if (user == null) {
            return "users/login";
        } else {
            model.addAttribute("user", user);
        }

        String newCompanyCode = newRequest.get("companyCode");
        String newRequestor = newRequest.get("requestor");
        String newConsultant = newRequest.get("consultant");
        String newSDate = newRequest.get("sDate");
        String newEDate = newRequest.get("eDate");
        String newEmail = newRequest.get("email");
        String newProjectName = newRequest.get("projectName");
        String newCostElement = newRequest.get("costElement");
        String newStatementOfWork = newRequest.get("statementOfWork");
        String newTotalCost = newRequest.get("totalCost");
        String newSourcingJustification = newRequest.get("sourcingJustification");
        String newStatus = newRequest.get("status");
        String newComments = newRequest.get("comments");
        int newUserID = user.getId();

        byte[] sjFileData = sjFile.getBytes();
        byte[] pcFileData = pcFile.getBytes();

        // print length of files
        System.out.println("sjFileData.length: " + sjFileData.length);
        System.out.println("pcFileData.length: " + pcFileData.length);

        Request newReq = new Request(newCompanyCode, newRequestor, newConsultant, newSDate, newEDate, newEmail,
                newProjectName, newCostElement, newStatementOfWork, newTotalCost, newSourcingJustification, newStatus,
                newUserID, (byte[]) sjFileData, (byte[]) pcFileData, user, newComments);

        requestRepo.save(newReq);

        return "redirect:/form";
    }

    @GetMapping("/requests/draft")
    public String getDrafts(Model model, HttpServletRequest request, HttpSession session) {
        System.out.println("Getting all draft requests");
        // get all requests from database
        User user = (User) session.getAttribute("session_user");
        if (user == null) {
            return "users/login";
        } else {
            model.addAttribute("user", user);
        }
        List<Request> requests = requestRepo.findAll();
        // end of database call
        model.addAttribute("requests", requests);
        System.out.println("Drafts:");
        return "users/formdrafts";
    }

    @GetMapping("/requests/submitted")
    public String getSubmitted(Model model, HttpServletRequest request, HttpSession session) {
        System.out.println("Getting all submitted requests");
        // get all requests from database
        User user = (User) session.getAttribute("session_user");
        if (user == null) {
            return "users/login";
        } else {
            model.addAttribute("user", user);
        }
        List<Request> requests = requestRepo.findAll();
        // end of database call
        model.addAttribute("requests", requests);
        return "users/formssubmitted";
    }

    @GetMapping("/edit/{rid}")
    public String editRequestById(Model model, @PathVariable("rid") int rid, HttpSession session) {

        Request request1 = requestRepo.findById(rid).get(0);
        byte[] sjFile = request1.getSjFile();
        byte[] pcFile = request1.getPcFile();

        System.out.println("sjFile: " + sjFile);
        System.out.println("pcFile: " + pcFile);

        System.out.println("sjFile.length: " + sjFile.length);
        System.out.println("pcFile.length: " + pcFile.length);

        boolean hasSavedSJFile = false;
        if (sjFile.length > 0) {
            hasSavedSJFile = true;
        }
        System.out.println("hasSavedSJFile: " + hasSavedSJFile);

        model.addAttribute("hasSavedSJFile", hasSavedSJFile);

        boolean hasSavedPCFile = false;
        if (pcFile.length > 0) {
            hasSavedPCFile = true;
        }
        System.out.println("hasSavedPCFile: " + hasSavedPCFile);
        model.addAttribute("hasSavedPCFile", hasSavedPCFile);

        User user = (User) session.getAttribute("session_user");
        if (user == null) {
            return "users/login";
        } else {
            model.addAttribute("user", user);
        }
        System.out.println("editRequesteById" + rid);

        List<Request> request = requestRepo.findById(rid);
        model.addAttribute("request", request.get(0));
        return "users/editform";
    }

    @GetMapping("/review/{rid}")
    public String reviewRequest(Model model, @PathVariable("rid") int rid, HttpSession session) {
        User user = (User) session.getAttribute("session_user");
        if (user == null) {
            return "users/login";
        } else {
            model.addAttribute("user", user);
        }
        System.out.println("reviewRequest" + rid);

        List<Request> request = requestRepo.findById(rid);
        model.addAttribute("request", request.get(0));
        return "users/reviewform";
    }

    @PostMapping("/requests/{rid}")
    public String saveUpdatedrequest(@RequestParam Map<String, String> newRequest, @PathVariable int rid, Model model,
            HttpSession session, @RequestParam("sjFile") MultipartFile sjFile,
            @RequestParam("pcFile") MultipartFile pcFile)
            throws IOException {

        System.out.println("saveUpdatedrequest" + rid);

        Request request2 = requestRepo.findById(rid).get(0);

        request2.setRid(rid);
        request2.setCompanyCode(newRequest.get("companyCode"));
        request2.setRequestor(newRequest.get("requestor"));
        request2.setConsultant(newRequest.get("consultant"));
        request2.setsDate(newRequest.get("sDate"));
        request2.seteDate(newRequest.get("eDate"));
        request2.setEmail(newRequest.get("email"));
        request2.setProjectName(newRequest.get("projectName"));
        request2.setCostElement(newRequest.get("costElement"));
        request2.setStatementOfWork(newRequest.get("statementOfWork"));
        request2.setTotalCost(newRequest.get("totalCost"));
        request2.setSourcingJustification(newRequest.get("sourcingJustification"));
        request2.setStatus(newRequest.get("status"));
        request2.setComments(newRequest.get("comments"));
        // only change the file if a new file is uploaded

        // Check if a new SJ File is provided
        System.out.println("sjFile:");
        if (sjFile.getBytes().length > 0) {
            byte[] sjFileData = sjFile.getBytes();
            request2.setSjFile(sjFileData);
        }

        // Check if a new PC File is provided
        if (pcFile.getBytes().length > 0) {
            byte[] pcFileData = pcFile.getBytes();
            request2.setPcFile(pcFileData);
        }

        User user = (User) session.getAttribute("session_user");
        if (user == null) {
            return "users/login";
        } else {
            model.addAttribute("user", user);
        }
        int newUserID = user.getId();

        request2.setUserID(newUserID);
        requestRepo.save(request2);

        return "redirect:/form";
    }

}
