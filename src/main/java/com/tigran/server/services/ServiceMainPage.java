package com.tigran.server.services;

import com.tigran.server.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class ServiceMainPage {

    @Autowired
    StorageService storageService;
    @Autowired
    ServiceTopLinks serviceTopLinks;
    @Autowired
    ServiceSuggestions serviceSuggestions;
    @Autowired
    ServicesAdvertisement servicesAdvertisement;
    @Autowired
    ServiceTopSuggestion serviceTopSuggestion;
    @Autowired
    ServiceStories serviceStories;

    @Value("${image.path}")
    String imagePath;

    public String delete(String id, String categories) {
        switch (categories) {
            case "topLinks":
                serviceTopLinks.delete(id);
                break;
            case "advertisement":
                servicesAdvertisement.delete(id);
                break;
            case "suggestions":
                serviceSuggestions.delete(id);
                break;
            case "stories":
                serviceStories.delete(id);
                break;
            case "topSuggestion":
                serviceTopSuggestion.delete(id);
                break;
        }
        return "Successful";
    }

    public String update(String id, String categories, MultipartFile image, MultipartFile contentImage, String newText) throws IOException {
        String newImageUuid = null;
        String newContentUuid = null;
        if(image != null){
            newImageUuid = storageService.saveImg(image);
        }
        if (contentImage != null){
            newContentUuid = storageService.saveImg(contentImage);
        }

        switch (categories) {
            case "topLinks":
                serviceTopLinks.update(id, newImageUuid, newText);
                break;
            case "advertisement":
                servicesAdvertisement.update(newImageUuid);
                break;
            case "suggestions":
                serviceSuggestions.update(id, newImageUuid);
                break;
            case "stories":
                serviceStories.update(id, newImageUuid, newContentUuid);
                break;
            case "topSuggestion":
                serviceTopSuggestion.update(id, newImageUuid, newContentUuid);
                break;
        }
        return "Successful";
    }

    public String set(MultipartFile image, MultipartFile contentImage, String text, String categories) throws IOException {

        File uploadDir = new File(imagePath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        String imageUuid = storageService.saveImg(image);
        String contentUuid = null;
        if (contentImage != null) {
          contentUuid = storageService.saveImg(contentImage);
        }

        switch (categories) {
            case "topLinks":
                serviceTopLinks.add(text, imageUuid);
                break;
            case "advertisement":
                servicesAdvertisement.add(imageUuid);
                break;
            case "suggestions":
                serviceSuggestions.add(imageUuid);
                break;
            case "stories":
                serviceStories.add(imageUuid, contentUuid);
                break;
            case "topSuggestion":
                serviceTopSuggestion.add(imageUuid, contentUuid);
                break;
        }

        return "Successful";
    }

}
