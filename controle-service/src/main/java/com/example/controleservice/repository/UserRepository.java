package com.example.controleservice.repository;

import com.example.controleservice.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Student, UUID> {

    Optional<Student> findByCpf(String cpf);
}
