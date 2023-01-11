package com.example.userservice.dto.userDtos;

import com.example.userservice.enums.ActionType;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDtoRabbit {
    private UUID id;
    private String name;
    private String lastName;
    private String phone;
    private ActionType actionType;
}
