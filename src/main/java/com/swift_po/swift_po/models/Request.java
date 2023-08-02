package com.swift_po.swift_po.models;

import jakarta.persistence.*;

@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;
    private String companyCode;
    private String requestor;
    private String consultant;
    private String sDate;
    private String eDate;
    private String email;
    private String projectName;
    private String costElement;
    private String statementOfWork;
    private String totalCost;
    private String sourcingJustification;
    private String status;
    private int userID;

    @Column(columnDefinition = "default NULL")
    private byte[] sjFile;
    @Column(columnDefinition = "default NULL")
    private byte[] pcFile;

    public Request() {
        this.sjFile = null;
        this.pcFile = null;
    }

    public Request(String companyCode, String requestor, String consultant, String sDate, String eDate, String email,
            String projectName, String costElement, String statementOfWork, String totalCost,
            String sourcingJustification, String status, int userID, byte[] sjFile, byte[] pcFile) {
        this.companyCode = companyCode;
        this.requestor = requestor;
        this.consultant = consultant;
        this.sDate = sDate;
        this.eDate = eDate;
        this.email = email;
        this.projectName = projectName;
        this.costElement = costElement;
        this.statementOfWork = statementOfWork;
        this.totalCost = totalCost;
        this.sourcingJustification = sourcingJustification;
        this.status = status;
        this.userID = userID;
        this.sjFile = sjFile;
        this.pcFile = pcFile;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getRequestor() {
        return requestor;
    }

    public void setRequestor(String requestor) {
        this.requestor = requestor;
    }

    public String getConsultant() {
        return consultant;
    }

    public void setConsultant(String consultant) {
        this.consultant = consultant;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getSjFile() {
        return sjFile;
    }

    public void setSjFile(byte[] sjFile) {
        this.sjFile = sjFile;
    }

    public byte[] getPcFile() {
        return pcFile;
    }

    public void setPcFile(byte[] pcFile) {
        this.pcFile = pcFile;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCostElement() {
        return costElement;
    }

    public void setCostElement(String costElement) {
        this.costElement = costElement;
    }

    public String getStatementOfWork() {
        return statementOfWork;
    }

    public void setStatementOfWork(String statementOfWork) {
        this.statementOfWork = statementOfWork;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getSourcingJustification() {
        return sourcingJustification;
    }

    public void setSourcingJustification(String sourcingJustification) {
        this.sourcingJustification = sourcingJustification;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getsDate() {
        return sDate;
    }

    public void setsDate(String sDate) {
        this.sDate = sDate;
    }

    public String geteDate() {
        return eDate;
    }

    public void seteDate(String eDate) {
        this.eDate = eDate;
    }
}
