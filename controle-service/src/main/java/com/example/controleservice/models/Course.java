package com.example.controleservice.models;

import com.example.controleservice.enums.EDaysOfWeek;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "TB_COURSE")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    private String nameUserCreate;
    @NotNull
    private Integer workload;
    @NotBlank
    private EDaysOfWeek daysOfWeek;
    private String numberRoom;
    private LocalDateTime lastChange;
//    @OneToMany
//    @JoinColumn(name = "id")
//    private List<User> students;
}
