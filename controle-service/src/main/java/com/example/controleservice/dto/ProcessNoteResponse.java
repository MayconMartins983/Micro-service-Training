package com.example.controleservice.dto;

import com.example.controleservice.enums.EStatusStudent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class ProcessNoteResponse {

    private UUID id;
    private String nameStudent;
    private EStatusStudent statusStudent;
    private Double noteFinal;
}
