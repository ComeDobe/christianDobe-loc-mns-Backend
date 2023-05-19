//package com.example.loctest.service;
//
//import com.example.loctest.dao.UsureDao;
//import com.example.loctest.entity.UsureEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class UsureService {
//
//    @Autowired
//    private UsureDao usureDao;
//
//    public List<UsureEntity> getAllUsures() {
//        return usureDao.findAll();
//    }
//
//    public UsureEntity getUsureById(int usureId) {
//        return usureDao.findById(usureId).orElse(null);
//    }
//
//    public UsureEntity addUsure(UsureEntity usure) {
//        return usureDao.save(usure);
//    }
//
//    public void deleteUsure(int usureId) {
//        usureDao.deleteById(usureId);
//    }
//
//    public UsureEntity updateUsure(UsureEntity usure) {
//        UsureEntity currentUsure = (UsureEntity) usureDao.findById().orElse(null);
//        if (currentUsure != null) {
//            currentUsure.setMateriel(usure.getMateriel());
//            currentUsure.setEtat(usure.getEtat());
//            currentUsure.setDateChangement(usure.getDateChangement());
//            return usureDao.save(currentUsure);
//        }
//        return null;
//    }
//
//    public List<UsureEntity> searchUsureByMateriel(int materielId) {
//        return usureDao.findAllByMaterielId(materielId);
//    }
//
//
//}
