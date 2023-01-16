package com.example.userservice.dto.authDtos;

import com.example.userservice.config.security.UserDetailsImpl;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Data
@Builder
public class UserAuthenticated {

    private UUID userId;
    private String username;
    private String lastName;
    private String phone;
    private String email;
    private String cpf;
    private Collection<? extends GrantedAuthority> roles;

    public static UserAuthenticated of(UserDetailsImpl userDetails) {
        return UserAuthenticated.builder()
                .userId(userDetails.getUserId())
                .username(userDetails.getUsername())
                .lastName(userDetails.getLastName())
                .phone(userDetails.getPhone())
                .email(userDetails.getEmail())
                .cpf(userDetails.getCpf())
                .roles(userDetails.getAuthorities())
                .build();
    }
}
