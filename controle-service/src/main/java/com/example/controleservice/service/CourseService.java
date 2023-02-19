package com.example.controleservice.service;

import com.example.controleservice.dto.CourseRequest;
import com.example.controleservice.dto.CourseResponse;
import com.example.controleservice.exceptions.ExceptionValidation;
import com.example.controleservice.exceptions.NotFoundException;
import com.example.controleservice.mapper.CourseMapper;
import com.example.controleservice.models.Course;
import com.example.controleservice.models.Student;
import com.example.controleservice.repository.CourseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {

    public static final String THERE_IS_ALREADY_A_COURSE_WITH_THIS_NAME = "there is already a course with this name!";
    @Autowired
    private CourseRepository repository;

    @Autowired
    private CourseMapper mapper;

    public CourseResponse createCourse(CourseRequest request) {
        verifyAlreadyExistsCourse(request.getName());
        var model = mapper.toCourse(request);
        model.setNameUserCreate("teste"); //TODO colocar nome do usuario autenticado

        return mapper.toCourseResponse(repository.save(model));
    }

    public List<CourseResponse> getAllLists() {
        return repository.findAll()
                .stream()
                .map(mapper::toCourseResponse)
                .collect(Collectors.toList());
    }

    public Page<CourseResponse> getAllPaged(@PageableDefault(size = 10,
            page = 0) Pageable pageable) {
        return repository.findAll(pageable).map(mapper::toCourseResponse);
    }

    public CourseResponse findById(UUID id) {
        var listVolei = findCourseByIdOrthrowException(id);
        return mapper.toCourseResponse(listVolei);
    }

    public void deleteCourse(UUID id) {
        var lista = findCourseByIdOrthrowException(id);

        repository.deleteById(lista.getId());
    }

    public Course getAllStudentsFromCourse(UUID courseId) {
        return findCourseByIdOrthrowException(courseId);
    }

    public CourseResponse updateCourse(UUID idCourse, CourseRequest request) {
        var courseModel = findCourseByIdOrthrowException(idCourse);
        if (request.getName().equals(courseModel.getName())) {
            verifyAlreadyExistsCourse(request.getName());
        }
        BeanUtils.copyProperties(request, courseModel);
        courseModel.setLastChange(LocalDateTime.now());

        repository.save(courseModel);
        return mapper.toCourseResponse(courseModel);
    }

    public void verifyAlreadyExistsCourse(String name) {
        if (repository.existsCourseByName(name)) {
            throw new ExceptionValidation(THERE_IS_ALREADY_A_COURSE_WITH_THIS_NAME);
        }
    }

    private Course findCourseByIdOrthrowException(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Course not found!!!"));
    }
}


