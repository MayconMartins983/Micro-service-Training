package com.example.userservice.repository;

import com.example.userservice.enums.Role;
import com.example.userservice.model.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RolesModelRepository extends JpaRepository<RolesModel, UUID> {

    boolean existsByRole(Role role);

    RolesModel findByRole(Role role);
}
