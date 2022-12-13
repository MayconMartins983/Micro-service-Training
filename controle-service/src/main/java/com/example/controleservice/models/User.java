package com.example.controleservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_USER")
public class User {

    @Id
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phone;
}
