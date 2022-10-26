package com.tigran.server.services;

import com.tigran.server.dao.StoriesDAOImpl;
import com.tigran.server.models.Stories;
import com.tigran.server.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ServiceStories {
    Map<String, Stories> storiesMap = new HashMap();

    @Autowired
    StoriesDAOImpl storiesDAO;
    @Autowired
    StorageService storageService;

    public List<Stories> get() {
        return new ArrayList<>(storiesMap.values());
    }

    @EventListener(ApplicationReadyEvent.class)
    public void StoriesUpdateFromDto() {
        List<Stories> list = storiesDAO.read();
        for (Stories stories : list) {
            storageService.add(stories.getImageUuid());
            storageService.add(stories.getContentUuid());
            storiesMap.put(stories.getId(), stories);
        }
    }

    public void add(String uuid, String contentUuid) {
        Stories stories = new Stories();
        stories.setId(UUID.randomUUID().toString());
        stories.setImageUuid(uuid);
        stories.setContentUuid(contentUuid);
        storiesDAO.create(stories);
        storiesMap.put(stories.getId(), stories);
    }

    public void update(String id, String newImageUuid, String newContentUuid) {
        Stories stories = storiesMap.get(id);
        if (stories == null) {
            return;
        }
        if (newImageUuid != null) {
            storageService.deleteImg(stories.getImageUuid());
            stories.setImageUuid(newImageUuid);
        }
        if (newContentUuid != null) {
            storageService.deleteImg(stories.getContentUuid());
            stories.setContentUuid(newContentUuid);
        }
        storiesDAO.update(id, stories);
    }

    public void delete(String id) {
        Stories stories = storiesMap.get(id);
        storageService.deleteImg(stories.getImageUuid());
        storiesDAO.delete(stories);
        storiesMap.remove(id);
    }
}
