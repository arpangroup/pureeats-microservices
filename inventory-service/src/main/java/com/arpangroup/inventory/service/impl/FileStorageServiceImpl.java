package com.arpangroup.inventory.service.impl;

import com.arpangroup.inventory.util.FileUtils;
import com.arpangroup.inventory.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service("FILE")
@Slf4j
public class FileStorageServiceImpl implements StorageService {

    @Value("${storage.directory:${ou.home}/storage/stores/}")
    private String uploadDir;

    @Override
    public String uploadFile(MultipartFile[] files) throws IOException {
        log.info("UPLOAD_DIR: {}", uploadDir);
        String fileUrl = "";
        try {
            for (MultipartFile file : files) {
                //File file = FileUtils.convertMultiPartToFile(multipartFile);
                String fileName = FileUtils.generateFileName(file);
                Path fileNameAndPath = Paths.get(uploadDir, fileName);
                Files.write(fileNameAndPath, file.getBytes());

                fileUrl = fileName;
            }
            return fileUrl;
        } catch (IOException ioe) {
            log.info("IOE Error Message: " + ioe.getMessage());
            throw ioe;
        }catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
    }

    @Override
    public byte[] downloadFile(String fileName) {
        Path path = Paths.get(uploadDir, fileName);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    @Override
    public String deleteFile(String fileName) {
        Path path = Paths.get(uploadDir, fileName);
        try {
            Files.delete(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
