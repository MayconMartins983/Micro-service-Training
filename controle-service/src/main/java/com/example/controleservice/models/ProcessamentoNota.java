package com.example.controleservice.models;

import com.example.controleservice.enums.ESemestre;
import com.example.controleservice.enums.EStatusStudent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_PROCESSAMENTO_NOTA")
@Data
@Builder
public class ProcessamentoNota {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="USER_ID", referencedColumnName = "id")
    private Student student;
    private String nameCourse;
    private ESemestre semester;
    private Double noteAct1;
    private Double noteAct2;
    private Double noteAct3;
    private Double noteAct4;
    private Double noteAct5;
    private Double noteProof1;
    private Double noteProof2;
    private Double noteProof3;
    private Boolean exam;
    private Double noteExam;
    private LocalDate dataProcessamento;
    private EStatusStudent statusAluno;
    private Boolean rounding;
    private String teacherConsiderations;

    public Double getSumNotes() {
        return  this.noteAct1 +
                this.noteAct2 +
                this.noteAct3 +
                this.noteAct4 +
                this.noteAct5 +
                this.noteProof1 +
                this.noteProof2 +
                this.noteProof3;
    }
}
