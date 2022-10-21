package com.tigran.server.controllers;

import com.tigran.server.models.*;
import com.tigran.server.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class MainPaigeController {

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
    @Autowired
    ServiceMainPage serviceMainPage;

    @GetMapping("/topLinks")
    public List<TopLinks> getTopLinks() {
        return serviceTopLinks.get();
    }

    @GetMapping("/suggestions")
    public List<Suggestions> getSuggestions() {
        return serviceSuggestions.get();
    }

    @GetMapping("/advertisement")
    public Advertisement getAdvertisement() {
        return servicesAdvertisement.get();
    }

    @GetMapping("/topSuggestion")
    public List<TopSuggestion> getTopSuggestion() {
        return serviceTopSuggestion.get();
    }

    @GetMapping("/stories")
    public List<Stories> getStories() {
        return serviceStories.get();
    }

    @PostMapping("/set")
    public String set(@RequestParam("categories") String categories,
                      @RequestParam("image") MultipartFile image,
                      @RequestParam(value = "contentImage", required = false) MultipartFile contentImage,
                      @RequestParam(value = "text", required = false) String text) throws IOException {
        return serviceMainPage.set(image, contentImage, text, categories);
    }

    @PostMapping("/update")
    public String update(@RequestParam("id") String id,
                         @RequestParam("categories") String categories,
                         @RequestParam(value = "image", required = false) MultipartFile image,
                         @RequestParam(value = "contentImage", required = false) MultipartFile contentImage,
                         @RequestParam(value = "text", required = false) String text) throws IOException {
        return serviceMainPage.update(id, categories, image, contentImage, text);
    }
    @DeleteMapping("/delete")
    public String delete(@RequestParam("id") String uuid,
                         @RequestParam("categories") String categories){
        return serviceMainPage.delete(uuid, categories);
    }
}

