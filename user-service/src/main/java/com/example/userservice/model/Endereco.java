package com.example.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Endereco {

    @NotBlank
    private String rua;
    @NotBlank
    private String numero;
    @NotBlank
    private String complemento;
    @NotBlank
    private String bairro;
    @NotBlank
    private String cidade;
    @NotBlank
    private String uf;
    @NotBlank
    private Integer cep;
}
