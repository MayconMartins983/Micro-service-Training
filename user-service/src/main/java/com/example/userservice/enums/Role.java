package com.example.userservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {
    ADMIN("Admin"),
    MODERATOR("Moderator"),
    USER("User");

    private String description;
}
