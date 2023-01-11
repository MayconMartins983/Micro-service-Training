package com.example.controleservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_STUDENT")
@Builder
public class Student {

    @Id
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    @NotBlank
    private String phone;

    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ProcessamentoNota> processamentoNotaList;

    public String getFullName() {
        return name.concat(" ").concat(lastName);
    }
}
