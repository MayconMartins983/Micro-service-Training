package com.example.controleservice.dto;

import com.example.controleservice.enums.EDaysOfWeek;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
