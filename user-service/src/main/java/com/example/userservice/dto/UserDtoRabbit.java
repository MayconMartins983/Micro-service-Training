package com.example.userservice.dto;

import com.example.userservice.enums.ActionType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class UserDtoRabbit {
    private UUID id;
    private String name;
    private String lastName;
    private String phone;
    private ActionType actionType;
}
