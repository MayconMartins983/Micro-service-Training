package com.example.userservice.dto.userDtos;

import com.example.userservice.model.Endereco;
import com.example.userservice.model.RolesModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embedded;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phone;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @Embedded
    private Endereco endereco;
    @NotNull
    private List<RolesModel> roles;
}
