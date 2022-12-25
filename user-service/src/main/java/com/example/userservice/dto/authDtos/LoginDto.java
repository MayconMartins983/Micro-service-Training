package com.example.userservice.dto.authDtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {

    @NotBlank
    private String userName;

    @NotBlank
    private String password;
}
