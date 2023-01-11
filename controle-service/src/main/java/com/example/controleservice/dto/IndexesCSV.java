package com.example.controleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class IndexesCSV {

    private Integer nameCourseIndex;
    private Integer semesterIndex;
    private Integer noteAct1Index;
    private Integer noteAct2Index;
    private Integer noteAct3Index;
    private Integer noteAct4Index;
    private Integer noteAct5Index;
    private Integer noteProof1Index;
    private Integer noteProof2Index;
    private Integer noteProof3Index;
    private Integer examIndex;
    private Integer noteExamIndex;
    private Integer statusAlunoIndex;
    private Integer roundingIndex;
    private Integer teacherConsiderationsIndex;
    private Integer cpfIndex;
}
