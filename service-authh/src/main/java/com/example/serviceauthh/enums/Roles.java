package com.example.serviceauthh.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Roles {
    ADMIN("Admin"),
    MODERATOR("Moderator"),
    USER("User");

    private String description;
}
