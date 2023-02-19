package com.example.controleservice.repository;

import com.example.controleservice.models.ProcessamentoNota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProcessamentoNotaRepository extends JpaRepository<ProcessamentoNota, UUID> {

    @Query("select p from TB_PROCESSAMENTO_NOTA p where p.student.id in :idStudent")
    List<ProcessamentoNota> findProcessByStudent(List<UUID> idStudent);
}
