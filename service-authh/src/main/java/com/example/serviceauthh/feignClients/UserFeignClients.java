package com.example.serviceauthh.feignClients;

import com.example.serviceauthh.model.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(value = "user-service", url = "localhost:8081/user")
public interface UserFeignClients {

    @GetMapping("/email")
    UserResponse findUserByEmailInClient(@RequestParam String email);
}
