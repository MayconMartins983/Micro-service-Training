package com.example.serviceauthh.controller;

import com.example.serviceauthh.model.UserResponse;
import com.example.serviceauthh.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public UserResponse findUserByEmail(@RequestParam String email) {
        return service.findByEmail(email);
    }
}
