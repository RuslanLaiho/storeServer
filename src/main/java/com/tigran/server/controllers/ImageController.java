package com.tigran.server.controllers;

import com.tigran.server.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

@RestController
public class ImageController {

    @Autowired
    StorageService storageService;

    @ResponseBody
    @GetMapping("/image")
    public ResponseEntity<byte[]> getImageAsResponseEntity(@RequestHeader("uuid") String uuid) throws IOException {

        String path = storageService.getFile(uuid);
        if(path==null){
            ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return responseEntity;
        }

        final File initialFile = new File(path);
        InputStream is =  new DataInputStream(new FileInputStream(initialFile));
        BufferedImage img = ImageIO.read(is);
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ImageIO.write(img, "jpg", bao);
        byte[] media = bao.toByteArray();

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(media, HttpStatus.OK);
        return responseEntity;
    }
}
