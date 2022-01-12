package com.arpangroup.inventory.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtils {

    public static File convertMultiPartToFile(MultipartFile file) throws IOException {
        if (file == null || file.getOriginalFilename() == null){
            return null;
        }
        File convertedFile = new File(file.getOriginalFilename());
        try(FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        }
        return convertedFile;
    }

    public static String generateFileName(MultipartFile file) {
        return System.currentTimeMillis() + "_" + file.getOriginalFilename().replace(" ", "_");
    }

    public static String generateFileName(File file) {
        return System.currentTimeMillis() + "_" + file.getName().replace(" ", "_");
    }
}
