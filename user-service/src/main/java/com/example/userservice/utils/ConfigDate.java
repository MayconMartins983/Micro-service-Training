package com.example.userservice.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConfigDate {

    public static String formatLocalDateTime(LocalDateTime date) {
         var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}
