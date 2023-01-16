package com.example.userservice.exceptions;

import com.example.userservice.utils.ConfigDate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(value = ResourceNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Object handleNotFoundException(ResourceNotFound ex) {
        var date = ConfigDate.formatLocalDateTime(LocalDateTime.now());
        var erroDescription = ex.getLocalizedMessage();

        if (erroDescription.isBlank()) {
            erroDescription = ex.toString();
        }

        return new ErroMessage(date, erroDescription);
    }

    @ExceptionHandler(value = ValidationExceptionCustom.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Object handleValidationException(ValidationExceptionCustom ex) {
        var date = ConfigDate.formatLocalDateTime(LocalDateTime.now());
        var erroDescription = ex.getLocalizedMessage();

        if (erroDescription.isBlank()) {
            erroDescription = ex.toString();
        }

        return new ErroMessage(date, erroDescription);
    }

//    @ExceptionHandler(value = AccessDeniedException.class)
//    @ResponseStatus(HttpStatus.FORBIDDEN)
//    @ResponseBody
//    public Object handleAcessDeniedException(AccessDeniedException ex) {
//        var date = ConfigDate.formatLocalDateTime(LocalDateTime.now());
//        var erroDescription = ex.getLocalizedMessage();
//
//        if (erroDescription.isBlank()) {
//            erroDescription = ex.toString();
//        }
//
//        return new ErroMessage(date, erroDescription);
//    }
}
