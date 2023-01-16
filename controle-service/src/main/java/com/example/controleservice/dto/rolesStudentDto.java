package com.example.controleservice.dto;

import org.springframework.security.core.GrantedAuthority;


public class rolesStudentDto implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
