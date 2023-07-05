package com.swift_po.swift_po.models;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;
    private String name;
    private String password;
    private String uName;
    public users() { 
    }
    public users(String name, String password, String uName) {
        this.name = name;
        this.password = password;
        this.uName = uName;
    }
    public String getuName(){
        return uName;
    }
    public void setuName(String uName){
        this.uName = uName;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getUid() {
        return uid;
    }
    public void setUid(int uid) {
        this.uid = uid;
    }
}
