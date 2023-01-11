package com.example.controleservice.controller;

import com.example.controleservice.dto.ProcessNoteResponse;
import com.example.controleservice.service.ProcessNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("process-note")
public class ProcessamentoNotaController {

    @Autowired
    private ProcessNoteService service;

    @PostMapping("csv/idStudent/{idStudent}")
    public ResponseEntity<List<ProcessNoteResponse>> processNotesFromCsv(MultipartFile file,
                                                                         @RequestParam String delimiter,
                                                                         @PathVariable UUID idStudent) {
        return ResponseEntity.ok().body(service.processNotesFromCsv(file, delimiter, idStudent));
    }
}
