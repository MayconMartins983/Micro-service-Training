package com.example.userservice.controller;

import com.example.userservice.dto.authDtos.JwtDto;
import com.example.userservice.dto.authDtos.LoginDto;
import com.example.userservice.dto.userDtos.UserRequest;
import com.example.userservice.dto.userDtos.UserResponse;
import com.example.userservice.service.AuthenticationService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public UserResponse createUser(@Validated @RequestBody UserRequest request) {
        return authenticationService.createUser(request);
    }

    @GetMapping("login")
    public ResponseEntity<JwtDto> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(authenticationService.authenticateUser(loginDto));
    }

    @GetMapping("check-token")
    public boolean checkTokenValid(@RequestParam String token) {
        return authenticationService.checkTokenValid(token);
    }
}
