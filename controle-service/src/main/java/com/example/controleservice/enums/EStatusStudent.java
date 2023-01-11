package com.example.controleservice.enums;

import lombok.Getter;

public enum EStatusStudent {
    APROVADO ("APROVADO"),
    REPROVADO ("REPROVADO"),
    EM_PROCESSAMENTO ("EM_PROCESSAMENTO"),
    IN_EXAM ("EM_PROCESSAMENTO");

    EStatusStudent(String descricao) {
    }

    @Getter
    private String descricao;
}
