package com.example.controleservice.enums;

import lombok.Getter;

public enum ESemestre {

    PRIMEIRO("PRIMEIRO"),
    SEGUNDO("SEGUNDO"),
    TERCEIRO("TERCEIRO"),
    QUARTO("QUARTO"),
    QUINTO("QUINTO"),
    SEXTO("SEXTO"),
    SETIMO("SETIMO"),
    OITAVO("OITAVO"),
    NONO("NONO"),
    DECIMO("DECIMO");

    ESemestre(String descricao) {
    }

    @Getter
    private String Descricao;
}
