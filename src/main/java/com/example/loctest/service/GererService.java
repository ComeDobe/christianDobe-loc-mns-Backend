package com.example.loctest.service;

import java.util.List;

import com.example.loctest.repository.GererDao;
import com.example.loctest.entity.GererEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GererService {

    @Autowired
    private GererDao gererDao;

    public List<GererEntity> getAllGerers() {
        return (List<GererEntity>) gererDao.findAll();
    }

    public GererEntity addGerer(GererEntity gerer) {
        return gererDao.save(gerer);
    }

    public void deleteGerer(int gererId) {
        gererDao.deleteById(gererId);
    }

    public GererEntity getGererById(int gererId) {
        return gererDao.getById(gererId);
    }
}
