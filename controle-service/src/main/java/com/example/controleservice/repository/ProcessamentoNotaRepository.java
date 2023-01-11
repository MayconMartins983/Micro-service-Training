package com.example.controleservice.repository;

import com.example.controleservice.models.ProcessamentoNota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProcessamentoNotaRepository extends JpaRepository<ProcessamentoNota, UUID> {
}
