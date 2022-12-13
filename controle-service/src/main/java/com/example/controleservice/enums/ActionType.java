package com.example.controleservice.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ActionType {
    CREATE("CREATE"),
    UPDATE("UPDATE"),
    DELETE("DELETE");

    private String description;
}
