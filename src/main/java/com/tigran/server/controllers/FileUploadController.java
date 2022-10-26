package com.tigran.server.controllers;

import com.tigran.server.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class FileUploadController {

    @Autowired
    StorageService storageService;

    @Value("${image.path}")
    String imagePath;

    @PostMapping("/imgUpload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   @RequestParam("text") String text) throws IOException {
        if(file != null){
            File uploadDir = new File(imagePath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuid = UUID.randomUUID().toString();
            String resultFileName = uuid;

            file.transferTo(new File(imagePath + "/" +resultFileName));
            storageService.add(uuid);

            return "Sucsesful";
        }
        return "File is absent";
    }


}
