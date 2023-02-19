package com.example.controleservice.repository;

import com.example.controleservice.models.Course;
import com.example.controleservice.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {

    boolean existsCourseByName(String name);

}
