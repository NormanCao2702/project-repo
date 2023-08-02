package com.swift_po.swift_po.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<User,Integer> {
    List<User> findByEmail(String email);
    List<User> findByEmailAndPassword(String email, String password);
    List<User> findByPasswordResetToken(String passwordResetToken);
    

}