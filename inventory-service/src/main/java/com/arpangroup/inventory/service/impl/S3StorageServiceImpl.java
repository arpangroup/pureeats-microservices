package com.arpangroup.inventory.service.impl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.arpangroup.inventory.util.FileUtils;
import com.arpangroup.inventory.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


@Service("S3")
@Slf4j
public class S3StorageServiceImpl implements StorageService {
    private final AmazonS3 s3Client;

    @Value("${cloud.aws.bucket.name}")
    private String bucketName;

    public S3StorageServiceImpl(AmazonS3 s3Client) {
        this.s3Client = s3Client;
    }


    @Override
    public String uploadFile(MultipartFile[] files) throws IOException {
        String fileUrl = "";
        try {
           for (MultipartFile multipartFile : files){
               File file = FileUtils.convertMultiPartToFile(multipartFile);
               String fileName = FileUtils.generateFileName(multipartFile);
               fileUrl = fileName;
               s3Client.putObject(new PutObjectRequest(bucketName, fileName, file));
               file.delete();
           }
        } /*catch (Exception e) {
			e.printStackTrace();
		}*/
        catch (AmazonServiceException ase) {
            log.info("Caught an AmazonServiceException from GET requests, rejected reasons:");
            log.info("Error Message:    " + ase.getMessage());
            log.info("HTTP Status Code: " + ase.getStatusCode());
            log.info("AWS Error Code:   " + ase.getErrorCode());
            log.info("Error Type:       " + ase.getErrorType());
            log.info("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            log.info("Caught an AmazonClientException: ");
            log.info("Error Message: " + ace.getMessage());
        } catch (IOException ioe) {
            log.info("IOE Error Message: " + ioe.getMessage());

        }
        return fileUrl;
    }

    @Override
    public byte[] downloadFile(String fileName) {
        byte[] content = null;
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try{
            content = IOUtils.toByteArray(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    @Override
    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return "successfully deleted";
    }

}
