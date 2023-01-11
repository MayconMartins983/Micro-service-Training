package com.example.controleservice.utils;

import com.example.controleservice.exceptions.ExceptionValidation;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.aspectj.util.FileUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CvsUtils {

    public static List<String> readCsv(MultipartFile file) {
        List<String> listEmpty = new ArrayList<>();

        if (file.isEmpty()) {
            throw new ExceptionValidation("The file cannot be empty");
        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            listEmpty = br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return listEmpty;
    }

    public static void validatedExtensionFile(MultipartFile file) {
        if (file != null) {
        var extension =  FilenameUtils.getExtension(file.getOriginalFilename());

        if (!extension.equals("csv")) {
            throw new ExceptionValidation("Extesion File is not CSV!!!");
        }
        }
    }
}
