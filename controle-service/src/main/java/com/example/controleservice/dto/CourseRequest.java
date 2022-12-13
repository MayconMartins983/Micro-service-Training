package com.example.controleservice.dto;

import com.example.controleservice.enums.DaysOfWeek;
import com.example.controleservice.models.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class CourseRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private Integer workload;
    @NotBlank
    private DaysOfWeek daysOfWeek;
    private String numberRoom;
}
