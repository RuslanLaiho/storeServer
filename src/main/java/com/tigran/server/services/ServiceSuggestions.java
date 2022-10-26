package com.tigran.server.services;

import com.tigran.server.dao.SuggestionsDAOImpl;
import com.tigran.server.models.Suggestions;
import com.tigran.server.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceSuggestions {

    private Map<String, Suggestions> suggestionsMap = new HashMap<>();
    @Autowired
    SuggestionsDAOImpl suggestionsDAOImpl;
    @Autowired
    StorageService storageService;


    public List<Suggestions> get() {
        return new ArrayList<>(suggestionsMap.values());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void SuggestionsUpdateFromDto() {
        List<Suggestions> list = suggestionsDAOImpl.read();
        for (Suggestions topLinks : list) {
            storageService.add(topLinks.getImageUuid());
            suggestionsMap.put(topLinks.getId(), topLinks);
        }
    }

    public void add(String uuid) {
        Suggestions suggestions = new Suggestions();
        suggestions.setId(UUID.randomUUID().toString());
        suggestions.setImageUuid(uuid);
        suggestionsDAOImpl.create(suggestions);
        suggestionsMap.put(suggestions.getId(), suggestions);
    }

    public void update(String id, String newImageUuid) {
        Suggestions suggestions = suggestionsMap.get(id);
        if(suggestions == null){return;}
        if (newImageUuid != null) {
            storageService.deleteImg(suggestions.getImageUuid());
            suggestions.setImageUuid(newImageUuid);
        }
        suggestionsDAOImpl.update(id, suggestions);
    }

    public void delete(String id) {
        Suggestions suggestions = suggestionsMap.get(id);
        if(suggestions==null){return;}
        storageService.deleteImg(suggestions.getImageUuid());
        suggestionsDAOImpl.delete(suggestions);
        suggestionsMap.remove(id);
    }
}
