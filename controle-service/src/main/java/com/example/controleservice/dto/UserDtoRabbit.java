package com.example.controleservice.dto;

import com.example.controleservice.enums.ActionType;
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
