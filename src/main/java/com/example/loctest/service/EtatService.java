package com.example.loctest.service;


import com.example.loctest.repository.EtatDao;
import com.example.loctest.entity.EtatEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EtatService {

    @Autowired
    private EtatDao etatDao;

    public List<EtatEntity> getAllEtats() {
        return (List<EtatEntity>) etatDao.findAll();
    }

    public EtatEntity getEtatById(int etatId) {
        return etatDao.findById(etatId).orElse(null);
    }

    public EtatEntity addEtat(EtatEntity etat) {
        return etatDao.save(etat);
    }

    public void deleteEtat(int etatId) {
        etatDao.deleteById(etatId);
    }

    public void updateEtat(EtatEntity currentEtat) {
        EtatEntity etat = etatDao.findById(currentEtat.getEtatId()).orElse(null);
        if (etat != null) {
            etat.setEtatCondition(currentEtat.getEtatCondition());
            etatDao.save(etat);
        }
    }

    public List<EtatEntity> searchEtatByCondition(String condition) {
        return etatDao.findByEtatConditionContainingIgnoreCase(condition);
    }
}

