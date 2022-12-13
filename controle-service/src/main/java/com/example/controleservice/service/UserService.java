package com.example.controleservice.service;

import com.example.controleservice.exceptions.NotFoundException;
import com.example.controleservice.models.User;
import com.example.controleservice.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        var userModel = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException("user NotFound"));

        BeanUtils.copyProperties(user, userModel);
        userRepository.save(userModel);
    }

    public void deleteUser(User user) {
        var userModel = userRepository.findById(user.getId())
                .orElseThrow(() -> new NotFoundException("user NotFound"));
        userRepository.deleteById(userModel.getId());
    }
}
