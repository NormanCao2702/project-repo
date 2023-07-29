package com.swift_po.swift_po.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;


import com.swift_po.swift_po.controllers.UserController;
import com.swift_po.swift_po.models.User;
import com.swift_po.swift_po.models.userRepo;
import com.swift_po.swift_po.services.UserServices;



@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean // This annotation creates a mock userRepo
    private userRepo userRepo;



    @Test
    public void testAddUser_ValidData_ReturnsRedirect() throws Exception {
        // Arrange
        Map<String, String> newuser = new HashMap<>();
        newuser.put("email", "test@example.com");
        newuser.put("name", "John Doe");
        newuser.put("password", "Test@1234"); // Meets password policy
        newuser.put("userType", "IS User");

        // Define the behavior of the mock userRepo
        when(userRepo.findByEmail("test@example.com")).thenReturn(Collections.emptyList());
        when(userRepo.save(any(User.class))).thenReturn(new User());

        // Act 
        MvcResult result = mockMvc.perform(post("/users/add")
                .param("email", "test@example.com")
                .param("name", "John Doe")
                .param("password", "Test@1234")
                .param("userType", "IS User"))
                .andReturn();

        // Assert
        assertEquals("users/login", result.getModelAndView().getViewName());
    }
    // @Test
    // public void emailAlreadyExists_signup() throws Exception{

    // }
    // @Test
    // public void login() throws Exception{

    // }

    
}