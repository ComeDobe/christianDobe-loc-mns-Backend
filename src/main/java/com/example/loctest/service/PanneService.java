//package com.example.loctest.service;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.List;
//
//import com.example.loctest.repository.PanneDao;
//import com.example.loctest.entity.PanneEntity;
//import com.example.loctest.service.strategy.FileStorageService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//@Service
//public class PanneService {
//
//    @Autowired
//    private PanneDao panneDao;
//
//    @Autowired
//    private FileStorageService fileStorageService;
//
//    public List<PanneEntity> getAllPannes() {
//        return (List<PanneEntity>) panneDao.findAll();
//    }
//
//    public PanneEntity getPanneById(int panneId) {
//        return panneDao.findById(panneId).orElse(null);
//    }
//
//    public PanneEntity addPanne(PanneEntity panne) throws IOException {
//
//        File imageFile = new File(panne.getImageUrl());
//
//        String imageUrl = fileStorageService.storeFile((MultipartFile) imageFile);
//
//        return panneDao.save(panne);
//    }
//
//    public void deletePanne(int panneId) {
//        panneDao.deleteById(panneId);
//    }
//
//    public void savePanne(PanneEntity panne) {
//        panneDao.save(panne);
//    }
//}

package com.example.loctest.service;

import java.io.IOException;
import java.util.List;

import com.example.loctest.repository.PanneDao;
import com.example.loctest.entity.PanneEntity;
import com.example.loctest.service.strategy.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PanneService {

    @Autowired
    private PanneDao panneDao;

    @Autowired
    private FileStorageService fileStorageService;

    public List<PanneEntity> getAllPannes() {
        return (List<PanneEntity>) panneDao.findAll();
    }

    public PanneEntity getPanneById(int panneId) {
        return panneDao.findById(panneId).orElse(null);
    }

    public PanneEntity addPanne(PanneEntity panne) throws IOException {

        MultipartFile file = null;
        if (file != null && !file.isEmpty()) {
            String imageUrl = fileStorageService.storeFile(file);
            panne.setImageUrl(imageUrl);
        }

        return panneDao.save(panne);
    }



    public void deletePanne(int panneId) {
        panneDao.deleteById(panneId);
    }

    public void savePanne(PanneEntity panne) {
        panneDao.save(panne);
    }
}
