package com.example.userservice.service;

import com.example.userservice.enums.Role;
import com.example.userservice.exceptions.ValidationExceptionCustom;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.model.RolesModel;
import com.example.userservice.model.User;
import com.example.userservice.repository.RolesModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RolesModelRepository repository;

    @Autowired
    private UserService userService;

    public void addNewRoleForUser(UUID id, Role role) {
        var roleModel = repository.findByRole(role);
        var user = userService.findUserOrThrowException(id);
        verifyModelAlreadyExistAndSave(roleModel, user);
        user.getRoles().add(roleModel);
        userService.saveUserWithoutValidation(user);
    }

    private void verifyModelAlreadyExistAndSave(RolesModel role, User user) {
        var rolesByUser = user.getRoles().stream().map(RolesModel::getRole).collect(Collectors.toList());
        if (rolesByUser.contains(role.getRole())) {
            throw new ValidationExceptionCustom("Already exists its role in User!!!");
        }
    }
}
