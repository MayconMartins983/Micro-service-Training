package com.example.controleservice.controller;

import com.example.controleservice.dto.CourseRequest;
import com.example.controleservice.dto.CourseResponse;
import com.example.controleservice.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    private CourseService service;

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CourseRequest request) {
        return new ResponseEntity(service.createCourse(request), HttpStatus.CREATED);
    }

    @GetMapping("paged")
    public ResponseEntity<Page<CourseResponse>> getAllCourses(Pageable pageable) {
        return ResponseEntity.ok(service.getAllPaged(pageable));
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>>getAllCourses() {
        return ResponseEntity.ok(service.getAllLists());
    }

    @GetMapping("id-course/{id}")
    public ResponseEntity<CourseResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @DeleteMapping("id-course/{id}")
    public ResponseEntity deleteList(@PathVariable UUID id) {
        service.deleteCourse(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id-course/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable UUID id, @RequestBody CourseRequest request) {
        return ResponseEntity.ok(service.updateCourse(id, request));
    }
}
