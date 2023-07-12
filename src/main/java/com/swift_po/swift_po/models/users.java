package com.swift_po.swift_po.models;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String password;
    private String email;
    private String fName;
    private String lName;
    private String phoneNumber;
    private String passPhrase;
    private String uType;
    public users() {
    }
    public users(String password, String email, String fName, String lName, String phoneNumber,
            String passPhrase, String uType) {
        this.password = password;
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.phoneNumber = phoneNumber;
        this.passPhrase = passPhrase;
        this.uType = uType;
    }
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getemail() {
        return email;
    }
    public void setuName(String email) {
        this.email = email;
    }
    public String getfName() {
        return fName;
    }
    public void setfName(String fName) {
        this.fName = fName;
    }
    public String getlName() {
        return lName;
    }
    public void setlName(String lName) {
        this.lName = lName;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getPassPhrase() {
        return passPhrase;
    }
    public void setPassPhrase(String passPhrase) {
        this.passPhrase = passPhrase;
    }
    public String getuType() {
        return uType;
    }
    public void setuType(String uType) {
        this.uType = uType;
    }
    
    
    
}
