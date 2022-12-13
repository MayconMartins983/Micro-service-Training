package com.example.serviceauthh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private UUID id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private Endereco endereco;
}
