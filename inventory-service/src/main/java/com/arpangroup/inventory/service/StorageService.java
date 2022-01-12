package com.arpangroup.inventory.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public interface StorageService {
    public String uploadFile(MultipartFile ...multipartFile) throws IOException;
    public byte[] downloadFile(String fileName);
    public String deleteFile(String fileName);
}
