package com.swift_po.swift_po.models;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface userRepo extends JpaRepository<users,Integer> {
    List<users> findByEmail(String email);
    List<users> findByEmailAndPassword(String email, String password);
}