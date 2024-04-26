package com.springboot.causeconnect.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.apache.tomcat.util.http.parser.MediaType;
import org.hibernate.boot.archive.spi.InputStreamAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import com.springboot.causeconnect.dto.FileResponse;
import com.springboot.causeconnect.services.FileService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/file")
public class FileUploadController {
        @Autowired
        private FileService fileService;

      public static String path = System.getProperty("user.dir") + "/backend/causeconnect/src/main/resources/static/images";


    @PostMapping("/upload")
    public ResponseEntity<FileResponse> fileUpload(@RequestParam("image") MultipartFile image){

        String fileName;
        try {
              fileName  =this.fileService.uploadImage(path,image);
            
        } catch (Exception e) {
           e.printStackTrace();
           return new ResponseEntity<>(new FileResponse(null,"image is not  successfully uplaoded"),HttpStatus.INTERNAL_SERVER_ERROR);
        }

      
    
      return new ResponseEntity<>(new FileResponse(fileName,"image is successfully uplaoded"),HttpStatus.OK);





    }


    @GetMapping(value="/images/{imageName}", produces=org.springframework.http.MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
        @PathVariable("imageName") String imageName, 
        HttpServletResponse response){

        InputStream resource  =  this.fileService.getImage(path, imageName);

        response.setContentType(org.springframework.http.MediaType.IMAGE_JPEG_VALUE);

        try {
          StreamUtils.copy(resource,response.getOutputStream());
        } catch (IOException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        





      
     




    }
}
