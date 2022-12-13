package com.example.userservice.dto;

import com.example.userservice.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
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
