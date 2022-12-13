package com.example.userservice.controller;

import com.example.userservice.enums.Role;
import com.example.userservice.model.RolesModel;
import com.example.userservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/id/{id}")
    public void createRoleInUser(@PathVariable UUID id, @RequestParam Role role) {
        roleService.addNewRoleForUser(id, role);
    }
}
