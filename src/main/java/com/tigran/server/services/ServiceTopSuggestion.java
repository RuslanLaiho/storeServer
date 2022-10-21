package com.tigran.server.services;

import com.tigran.server.dao.TopSuggestionDAOImpl;
import com.tigran.server.models.TopSuggestion;
import com.tigran.server.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceTopSuggestion {

    private Map<String, TopSuggestion> topSuggestionMap = new HashMap<>();

    @Autowired
    TopSuggestionDAOImpl topSuggestionDAO;
    @Autowired
    StorageService storageService;

    public List<TopSuggestion> get(){
        return new ArrayList<>(topSuggestionMap.values());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void TopSuggestionUpdateFromDto() {
        List<TopSuggestion> list = topSuggestionDAO.read();
        for (TopSuggestion topSuggestion:list) {
            topSuggestionMap.put(topSuggestion.getId(), topSuggestion);
        }
    }

    public void add(String uuid, String contentUuid) {
        TopSuggestion topSuggestion = new TopSuggestion();
        topSuggestion.setId(UUID.randomUUID().toString());
        topSuggestion.setImageUuid(uuid);
        topSuggestion.setContentUuid(contentUuid);
        topSuggestionDAO.create(topSuggestion);
        topSuggestionMap.put(topSuggestion.getId(),topSuggestion);
    }

    public void update(String id, String newImageUuid, String newContentUuid) {
        TopSuggestion topSuggestion = topSuggestionMap.get(id);
        if (topSuggestion == null) {
            return;
        }
        if (newImageUuid != null) {
            storageService.deleteImg(topSuggestion.getImageUuid());
            topSuggestion.setImageUuid(newImageUuid);
        }
        if (newContentUuid != null) {
            storageService.deleteImg(topSuggestion.getContentUuid());
            topSuggestion.setContentUuid(newContentUuid);
        }
        topSuggestionDAO.update(id, topSuggestion);
    }
    public void delete(String id) {
        TopSuggestion topSuggestion = topSuggestionMap.get(id);
        storageService.deleteImg(topSuggestion.getImageUuid());
        topSuggestionDAO.delete(topSuggestion);
        topSuggestionMap.remove(id);
    }

}
