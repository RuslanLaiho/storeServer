package com.tigran.server.services;

import com.tigran.server.dao.AdvertisementDAOImpl;
import com.tigran.server.models.Advertisement;
import com.tigran.server.utils.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ServicesAdvertisement {

    Advertisement advertisement;

    @Autowired
    AdvertisementDAOImpl advertisementDAO;
    @Autowired
    StorageService storageService;

    public Advertisement get() {
        return advertisement;
    }

    @EventListener(ApplicationReadyEvent.class)
    void TopLinksUpdateFromDto() {
        List<Advertisement> advertisements = advertisementDAO.read();
        if(advertisements.size() > 0){
            advertisement = advertisements.get(0);
        }
    }

    public void add(String imageUuid) {
        if(advertisement == null) {
            advertisement = new Advertisement();
            advertisement.setId(UUID.randomUUID().toString());
            advertisement.setImageUuid(imageUuid);
            advertisementDAO.create(advertisement);
        } else {
            storageService.deleteImg(advertisement.getImageUuid());
            advertisement.setImageUuid(imageUuid);
            advertisementDAO.update(advertisement.getId(), advertisement);
        }
    }

    public void update(String newImageUuid) {
        if(advertisement!=null) {
            if (newImageUuid != null) {
                storageService.deleteImg(advertisement.getImageUuid());
                advertisement.setImageUuid(newImageUuid);
            }
            advertisementDAO.update(advertisement.getId(), advertisement);
        }
    }

    public void delete(String id) {
        storageService.deleteImg(advertisement.getImageUuid());
        advertisementDAO.delete(advertisement);
        advertisement = null;
    }
}
