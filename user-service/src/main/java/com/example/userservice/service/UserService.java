package com.example.userservice.service;

import com.example.userservice.dto.userDtos.UserRequest;
import com.example.userservice.dto.userDtos.UserResponse;
import com.example.userservice.enums.Role;
import com.example.userservice.exceptions.ResourceNotFound;
import com.example.userservice.exceptions.ValidationExceptionCustom;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.model.RolesModel;
import com.example.userservice.model.User;
import com.example.userservice.rabbitMq.UserPublisherRabbitMq;
import com.example.userservice.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.example.userservice.enums.ActionType.DELETE;
import static com.example.userservice.enums.ActionType.UPDATE;


@Service
public class UserService {

    public static final String RESOURCE_NOT_FOUND = ("User not exists!");
    public static final String EMAIL_IS_ALREADY_IN_USE = "Email is already in use!";
    public static final String THE_OBJECT_CANNOT_BE_EMPTY = "The object cannot be empty!";
    @Autowired
    private UserRepository repository;

    @Autowired
    private UserPublisherRabbitMq userPublisherRabbitMq;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthenticationService authenticationService;

    public Page<UserResponse> listAllUsers(Pageable pageable) {
        return repository.findAll(pageable)
                .map(userMapper::toUserResponse);
    }

    public UserResponse findByEmail(String email) {
        var user = repository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFound("User not found with email: " + email));
        return userMapper.toUserResponse(user);
    }

    public Set<Role> findAllRolesByUser(UUID id) {
        var user = findUserOrThrowException(id);

        return user.getRoles()
                .stream()
                .map(RolesModel::getRole)
                .collect(Collectors.toSet());
    }

    @Transactional
    public UserResponse updateUser(UserRequest request, UUID id) {
        var userModel = findUserOrThrowException(id);
        if (!request.getEmail().equalsIgnoreCase(userModel.getEmail())) {
            emailAlreadyExists(request.getEmail());
        }
        BeanUtils.copyProperties(request, userModel);
        repository.save(userModel);

        var userRabbit = userMapper.toUserRabbitFromModel(userModel);
        userPublisherRabbitMq.publisherUser(userRabbit, UPDATE);

        return userMapper.toUserResponse(userModel);
    }

    @SneakyThrows
    public UserResponse findById(UUID id) {
        var currentUser = authenticationService.getUserAuthenticated();

//        if (currentUser.getUserId().equals(id)) {
//            var user = findUserOrThrowException(id);
//            return userMapper.toUserResponse(user);
//        } else {throw new AccessDeniedException("Forbidden");
//        }

        var user = findUserOrThrowException(id);
        return userMapper.toUserResponse(user);

    }

    public void deleteUser(UUID id) {
        var user = findUserOrThrowException(id);
        repository.deleteById(id);

        var userRabbit = userMapper.toUserRabbitFromModel(user);

        userPublisherRabbitMq.publisherUser(userRabbit, DELETE);
    }

    public User saveUserWithoutValidation(User user) {
        return repository.save(user);
    }

    public User findUserOrThrowException(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFound(RESOURCE_NOT_FOUND));
    }

    private void emailAlreadyExists(String email) {
        if (repository.existsByEmail(email)) {
            throw new ValidationExceptionCustom(EMAIL_IS_ALREADY_IN_USE);
        }
    }
}
