package com.example.controleservice.mapper;

import com.example.controleservice.dto.CourseRequest;
import com.example.controleservice.dto.CourseResponse;
import com.example.controleservice.models.Course;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Course toCourse(CourseRequest request) {
        return modelMapper.map(request, Course.class);
    }

    public CourseResponse toCourseResponse(Course model) {
        return modelMapper.map(model, CourseResponse.class);
    }
}
