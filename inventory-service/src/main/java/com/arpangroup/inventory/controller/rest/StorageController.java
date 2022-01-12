package com.arpangroup.inventory.controller.rest;

import com.arpangroup.inventory.registry.StorageRegistry;
import com.arpangroup.inventory.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;

import static com.arpangroup.inventory.controller.rest.BaseApiController.BASE_URI;

@RestController
@RequestMapping(BASE_URI + "/file")
public class StorageController {
    @Autowired
    StorageRegistry storageRegistry;


    @Value("${storage.provider:FILE}")
    private String storageProvider;



    @PostMapping(value = {"", "/upload"})
    public ResponseEntity<String> uploadFile(@RequestPart(value = "file") @NotNull MultipartFile file) throws IOException{
        return new ResponseEntity<>(storageRegistry.getService(storageProvider).uploadFile(file), HttpStatus.OK);
    }

    @GetMapping(value = {"/{fileName}", "/download/{fileName}"})
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String fileName) throws IOException{
        byte[] data =  storageRegistry.getService(storageProvider).downloadFile(fileName);
        ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + fileName + "\"  ")
                .body(resource);
    }

    @DeleteMapping(value = {"/{fileName}", "/delete/{fileName}"})
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) throws IOException{
        return new ResponseEntity<>(storageRegistry.getService(storageProvider).deleteFile(fileName), HttpStatus.OK);
    }


}
