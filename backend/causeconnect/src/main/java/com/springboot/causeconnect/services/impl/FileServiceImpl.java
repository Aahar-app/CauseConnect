package com.springboot.causeconnect.services.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.util.UUID;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.causeconnect.services.FileService;

@Service
public class FileServiceImpl implements FileService{

    
    
    public FileServiceImpl() throws IOException{


    }

   

    public String uploadImage( String path, MultipartFile file){

        
        String name = file.getOriginalFilename();

        
        String randomId = UUID.randomUUID().toString();
        String hashFileName  = randomId.concat(name.substring(name.lastIndexOf(".")));



        // String filePath = path + File.separator + hashFileName;

        // File f  =new File(path);
        // if(!f.exists()){
        //     f.mkdir();

        // }


        // try {
        //     Files.copy(file.getInputStream(),Paths.get(filePath));
        // } catch (IOException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }

        Path fileNameAndPath = Paths.get(path,hashFileName);
        String x = null;
        try {
            Files.write(fileNameAndPath, file.getBytes());
            x =fileNameAndPath.toString();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return x;

    }



    @Override
    public InputStream getImage(String path, String name){

        String filePath = path + File.separator + name;
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(filePath);
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------");
        
            InputStream is=null;
            try {
                is = new FileInputStream(filePath);
            } catch (FileNotFoundException e) {
                System.out.println("fileeeeeeeeeee nottttttttt foundddddddddd");
                e.printStackTrace();
            }
        
        

        return is;

    }


}
