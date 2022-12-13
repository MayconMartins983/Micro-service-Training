package com.example.userservice.mapper;

import com.example.userservice.dto.UserDtoRabbit;
import com.example.userservice.dto.UserRequest;
import com.example.userservice.dto.UserResponse;
import com.example.userservice.enums.ActionType;
import com.example.userservice.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

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
