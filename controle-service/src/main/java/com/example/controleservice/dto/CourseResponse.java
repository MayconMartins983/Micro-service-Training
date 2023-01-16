package com.example.controleservice.dto;

import com.example.controleservice.enums.EDaysOfWeek;
import com.example.controleservice.models.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CourseResponse {

    private UUID id;
    private String name;
    private String description;
    private Integer workload;
    private EDaysOfWeek daysOfWeek;
    private String numberRoom;
    @JsonIgnore
    private List<Student> students;
}
