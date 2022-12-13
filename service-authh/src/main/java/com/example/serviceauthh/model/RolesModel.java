package com.example.serviceauthh.model;

import com.example.serviceauthh.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolesModel {

    private UUID id;
    private Roles role;
}
