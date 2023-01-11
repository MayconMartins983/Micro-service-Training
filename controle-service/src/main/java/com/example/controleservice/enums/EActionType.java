package com.example.controleservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EActionType {
    CREATE("CREATE"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private String description;
}
