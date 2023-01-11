package com.example.userservice.controller;

import com.example.userservice.config.security.JwtProvider;
import com.example.userservice.dto.authDtos.JwtDto;
import com.example.userservice.dto.authDtos.LoginDto;
import com.example.userservice.dto.userDtos.UserRequest;
import com.example.userservice.dto.userDtos.UserResponse;
import com.example.userservice.service.RoleService;
import com.example.userservice.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping
    public UserResponse createUser(@Validated @RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("login")
    public ResponseEntity<JwtDto> authenticateUser(@Valid @RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUserName(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwt(authentication);
        return ResponseEntity.ok(new JwtDto(jwt));
    }
}
