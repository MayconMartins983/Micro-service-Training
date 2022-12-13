package com.example.controleservice.models;

import com.example.controleservice.enums.DaysOfWeek;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
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
    private DaysOfWeek daysOfWeek;
    private String numberRoom;
    private LocalDateTime lastChange;
//    @OneToMany
//    @JoinColumn(name = "id")
//    private List<User> students;
}
