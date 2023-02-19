package com.example.userservice;

import com.example.userservice.exceptions.ResourceNotFound;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import com.example.userservice.service.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

class MyClass {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    private static Stream<Arguments> argumentProvider() {
        return Stream.of(
                Arguments
                        .of(UUID.fromString("2704f22a-52f4-4ff6-8e47-dd2298ec367a"), true, new User("UserAdmin")),
                Arguments
                        .of(UUID.fromString("2704f22a-52f4-4ff6-8e47-dd2298ec367a"), false, ResourceNotFound.class)

        );
    }

    @ParameterizedTest
    @MethodSource("argumentProvider")
    void myTest(UUID id, boolean userFound, final Class<? extends Throwable> exception) {
        if (userFound) {
            when(repository.findById(UUID.fromString("2704f22a-52f4-4ff6-8e47-dd2298ec367a")))
                    .thenReturn(Optional.of(new User("UserAdmin")));

            var user = service.findUserOrThrowException(id);

            assertThat(user).isEqualTo(new User("UserAdmin"));
        } else {
            when(repository.findById(UUID.fromString("2704f22a-52f4-4ff6-8e47-dd2298ec367a")))
                    .thenReturn(Optional.empty());

            var actual = org.junit.jupiter.api.Assertions.assertThrows(exception
                    , ()  -> service.findUserOrThrowException(id));

            assertThat(exception).isEqualTo(actual.getClass());
        }
    }
}
