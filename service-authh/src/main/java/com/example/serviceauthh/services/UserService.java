package com.example.serviceauthh.services;

import com.example.serviceauthh.exceptions.NotFoundException;
import com.example.serviceauthh.feignClients.UserFeignClients;
import com.example.serviceauthh.model.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserFeignClients feignClient;

    public UserResponse findByEmail(String email) {
        var user = feignClient.findUserByEmailInClient(email);
        if (user == null) {
            throw new NotFoundException("User not found in service user-service");
        }
        log.info("Email: {}, found", email);
        return user;
    }
}
