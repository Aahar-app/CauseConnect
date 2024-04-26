package com.springboot.causeconnect.services;

import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadImage(String path,MultipartFile file);

    InputStream getImage(String path, String name);

}
