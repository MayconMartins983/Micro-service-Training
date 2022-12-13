package com.example.controleservice.dto;

import com.example.controleservice.enums.DaysOfWeek;
import com.example.controleservice.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CourseResponse {

    private UUID id;
    private String name;
    private String description;
    private Integer workload;
    private DaysOfWeek daysOfWeek;
    private String numberRoom;
}
