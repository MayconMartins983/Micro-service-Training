package com.example.serviceauthh.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {

    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private Integer cep;
}
