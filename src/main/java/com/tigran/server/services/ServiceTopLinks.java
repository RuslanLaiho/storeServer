package com.tigran.server.services;

import com.tigran.server.dao.TopLinksDAOImpl;
import com.tigran.server.models.TopLinks;
import com.tigran.server.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceTopLinks {

    Map<String, TopLinks> topLinksMap = new HashMap();

    @Autowired
    TopLinksDAOImpl topLinksDAO;
    @Autowired
    StorageService storageService;

    @EventListener(ApplicationReadyEvent.class)
    public void TopLinksUpdateFromDto() {
        List<TopLinks> list = topLinksDAO.readAll();
        for (TopLinks topLinks:list) {
            topLinksMap.put(topLinks.getId(), topLinks);
        }
    }

    public List<TopLinks> get() {
        return new ArrayList<>(topLinksMap.values());
    }

    public void add(String text, String uuid) {
        TopLinks topLinks = new TopLinks();
        topLinks.setId(UUID.randomUUID().toString());
        topLinks.setImageUuid(uuid);
        topLinks.setTitle(text);
        topLinksDAO.create(topLinks);
        topLinksMap.put(topLinks.getId(), topLinks);
    }

    public void update(String id, String newImageUuid, String newText) {
        TopLinks topLinks = topLinksMap.get(id);
        if (newImageUuid != null) {
            storageService.deleteImg(topLinks.getImageUuid());
            topLinks.setImageUuid(newImageUuid);
        }
        if (newText != null){
            topLinks.setTitle(newText);
        }
        topLinksDAO.update(id, topLinks);
    }

    public void delete(String id) {
        TopLinks topLinks = topLinksMap.get(id);
        storageService.deleteImg(topLinks.getImageUuid());
        topLinksDAO.delete(topLinks);
        topLinksMap.remove(id);
    }
}
