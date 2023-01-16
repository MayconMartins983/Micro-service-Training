package com.example.userservice.mapper;

import com.example.userservice.dto.authDtos.UserAuthenticated;
import com.example.userservice.dto.userDtos.UserDtoRabbit;
import com.example.userservice.dto.userDtos.UserRequest;
import com.example.userservice.dto.userDtos.UserResponse;
import com.example.userservice.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    @Autowired
    private ModelMapper modelMapper;

    public User toUser(UserRequest userRequest) {
        return modelMapper.map(userRequest, User.class);
    }

    public UserResponse toUserResponse(User user) {
        return modelMapper.map(user, UserResponse.class);
    }

    public UserDtoRabbit toUserRabbitFromModel(User user) {
        return modelMapper.map(user, UserDtoRabbit.class);
    }

    public UserRequest toUserRequest(User user) {
        return modelMapper.map(user, UserRequest.class);
    }
}
