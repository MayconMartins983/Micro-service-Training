package com.example.controleservice.controller;

import com.example.controleservice.dto.ProcessNoteResponse;
import com.example.controleservice.service.ProcessNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("process-note")
public class ProcessamentoNotaController {

    @Autowired
    private ProcessNoteService service;

    @PostMapping("csv")
    public ResponseEntity<List<ProcessNoteResponse>> processNotesFromCsv(MultipartFile file,
                                                                         @RequestParam String delimiter) {
        return ResponseEntity.ok().body(service.processNotesFromCsv(file, delimiter));
    }

    @GetMapping("download-csv")
    public ResponseEntity<InputStreamResource> downloadCSV() throws IOException {
        return ResponseEntity.ok()
                .body(service.downloadCSV());
    }
}
