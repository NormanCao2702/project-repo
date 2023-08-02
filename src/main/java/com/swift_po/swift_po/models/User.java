package com.swift_po.swift_po.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String name;
    private String password;
    private String userType;
    private int numberForms;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Request> forms;

    private String avatarImagePath;
    private String passwordResetToken;

    public User() {
    }

    public User(String email, String name, String password, String userType, String avatarImagePath) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.userType = userType;
        this.avatarImagePath = avatarImagePath;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<Request> getForms() {
        return forms;
    }

    public void setForms(List<Request> forms) {
        this.forms = forms;
    }

    public int getNumberForms() {
        this.numberForms = forms.size();
        if (this.equals(null))
            return 0;
        return numberForms;
    }

    public String getAvatarImagePath() {
        return avatarImagePath;
    }

    public void setAvatarImagePath(String avatarImagePath) {
        this.avatarImagePath = avatarImagePath;
    }

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }
}
