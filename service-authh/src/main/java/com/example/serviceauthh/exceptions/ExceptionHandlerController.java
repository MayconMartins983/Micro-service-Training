package com.example.serviceauthh.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerController {

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<List<Message>> notFound(NotFoundException ex) {
        return new ResponseEntity<>(Collections.singletonList(new Message(ex.getMessage())), HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Message> exceptionHandler(Exception ex) {
        return new ResponseEntity<>(new Message(ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private class Message {
        private String message;
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String field;

        public Message(String message) {
            this.message = message;
        }
    }
}
