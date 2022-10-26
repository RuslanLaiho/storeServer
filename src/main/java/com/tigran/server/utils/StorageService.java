package com.tigran.server.utils;

import com.tigran.server.models.Image;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class StorageService {
    private Map<String, Image> imageMap = new HashMap<>();

    @Value("${image.path}")
    String imagePath;

    public void add(String uuid){
        Image image = new Image();
        image.setFileName(uuid);
        image.setUuid(uuid);
        imageMap.put(uuid, image);
    }

    public String getFile(String uuid){
        Image image = imageMap.get(uuid);
        if (image!=null){
            return imagePath + "/" + image.getFileName();
        }
        return null;
    }

    public String saveImg(MultipartFile image) throws IOException {
        String uuid = UUID.randomUUID().toString();
        String insPath = imagePath  + "/" + uuid;
        Files.write(Paths.get(insPath), image.getBytes());
        add(uuid);
        return uuid;
    }

    public void deleteImg(String uuidImage){
        Image image = imageMap.get(uuidImage);
        if(image!=null) {
            File file = new File(imagePath+'/'+image.getFileName());
            file.delete();
        }
    }
}
