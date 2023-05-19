package com.example.loctest.service;

import java.util.List;

import com.example.loctest.repository.PanneDao;
import com.example.loctest.entity.PanneEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PanneService {

    @Autowired
    private PanneDao panneDao;

    public List<PanneEntity> getAllPannes() {
        return (List<PanneEntity>) panneDao.findAll();
    }

    public PanneEntity getPanneById(int panneId) {
        return panneDao.findById(panneId).orElse(null);
    }

    public PanneEntity addPanne(PanneEntity panne) {
        return panneDao.save(panne);
    }

    public void deletePanne(int panneId) {
        panneDao.deleteById(panneId);
    }
}
