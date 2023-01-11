package com.example.controleservice.dto;

import com.example.controleservice.enums.EActionType;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDtoRabbit {
    private UUID id;
    private String name;
    private String lastName;
    private String phone;
    private EActionType EActionType;
}
