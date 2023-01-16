package com.example.userservice.service;

import com.example.userservice.config.security.JwtProvider;
import com.example.userservice.config.security.UserDetailsImpl;
import com.example.userservice.dto.authDtos.JwtDto;
import com.example.userservice.dto.authDtos.LoginDto;
import com.example.userservice.dto.authDtos.UserAuthenticated;
import com.example.userservice.dto.userDtos.UserRequest;
import com.example.userservice.dto.userDtos.UserResponse;
import com.example.userservice.exceptions.ValidationExceptionCustom;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.rabbitMq.UserPublisherRabbitMq;
import com.example.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.example.userservice.enums.ActionType.CREATE;
import static com.example.userservice.service.UserService.EMAIL_IS_ALREADY_IN_USE;

@Service
public class AuthenticationService {

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserPublisherRabbitMq userPublisherRabbitMq;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public JwtDto authenticateUser(LoginDto loginDto) {
        //metodo abaixo faz a autenticação do usuario, verifcia email e senha
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getUserEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwt(authentication);
        return new JwtDto(jwt);
    }

    @Transactional
    public UserResponse createUser(UserRequest request) {
        emailAlreadyExists(request.getEmail());
        var userModel = userMapper.toUser(request);
        userModel.setPassword(passwordEncoder.encode(request.getPassword()));
        var modelSaved = repository.save(userModel);
        var userRabbit = userMapper.toUserRabbitFromModel(modelSaved);

        userPublisherRabbitMq.publisherUser(userRabbit, CREATE);
        return userMapper.toUserResponse(modelSaved);
    }

    public UserAuthenticated getUserAuthenticated() {
        var userDetails = (UserDetailsImpl) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        return UserAuthenticated.of(userDetails);
    }

    public boolean checkTokenValid(String token) {
        return jwtProvider.validateJwt(token);
    }

    private void emailAlreadyExists(String email) {
        if (repository.existsByEmail(email)) {
            throw new ValidationExceptionCustom(EMAIL_IS_ALREADY_IN_USE);
        }
    }
}
