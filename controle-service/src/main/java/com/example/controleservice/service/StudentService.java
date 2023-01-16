package com.example.controleservice.service;

import com.example.controleservice.exceptions.NotFoundException;
import com.example.controleservice.models.Student;
import com.example.controleservice.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    @Autowired
    private UserRepository userRepository;

    public void saveStudent(Student student) {
        userRepository.save(student);
    }

    public void updateStudent(Student student) {
        var userModel = userRepository.findById(student.getId())
                .orElseThrow(() -> new NotFoundException("user NotFound"));

        BeanUtils.copyProperties(student, userModel);
        userRepository.save(userModel);
    }

    public Student findStudentByCpf(String cpf) {
        return userRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("This User Not Found"));
    }

    public void deleteStudent(Student student) {
        var userModel = userRepository.findById(student.getId())
                .orElseThrow(() -> new NotFoundException("user NotFound"));
        userRepository.deleteById(userModel.getId());
    }
}
