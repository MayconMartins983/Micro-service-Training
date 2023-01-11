package com.example.userservice.controller;

import com.example.userservice.dto.userDtos.UserRequest;
import com.example.userservice.dto.userDtos.UserResponse;
import com.example.userservice.enums.Role;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RequestMapping("user")
@RestController
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("id/{id}")
    public UserResponse findById(@PathVariable UUID id) {
        return service.findById(id);
    }

    @GetMapping("email")
    public UserResponse findByEmail(@RequestParam String email) {
        return service.findByEmail(email);
    }

    @GetMapping
    public Page<UserResponse> findByAllUsers(Pageable pageable) {
        return service.listAllUsers(pageable);
    }

    @PutMapping("id/{id}")
    public UserResponse updateUser(@Validated @PathVariable UUID id, @RequestBody UserRequest request) {
        return service.updateUser(request, id);
    }

    @GetMapping("roles-user/id/{id}")
    public Set<Role> findAllRolesByUser(@PathVariable UUID id) {
        return service.findAllRolesByUser(id);
    }

    @DeleteMapping("id/{id}")
    public void deleteUser(@PathVariable UUID id) {
        service.deleteUser(id);
    }
}
