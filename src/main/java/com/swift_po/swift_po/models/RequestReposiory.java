package com.swift_po.swift_po.models;

import java.util.List;
// import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestReposiory extends JpaRepository<Request, Integer> {
    List<Request> findById(int rid);
    List<Request> findByStatus(String status);
    List<Request> getEmployeeByRid(Long long1);
}