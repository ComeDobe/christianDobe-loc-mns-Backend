package com.example.loctest.service;

import com.example.loctest.repository.SuiviDao;
import com.example.loctest.entity.SuiviEntity;
import com.example.loctest.entity.PanneEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SuiviService {

    @Autowired
    private SuiviDao suiviDao;

    @Autowired
    private PanneService panneService;

    public List<SuiviEntity> getAllSuivis() {
        return suiviDao.findAll();
    }

    public SuiviEntity getSuiviById(int suiviId) {
        return suiviDao.findById(suiviId).orElse(null);
    }

    public SuiviEntity addSuivi(SuiviEntity suivi) {
        int panneId = suivi.getPanne().getPanneId();
        PanneEntity panne = panneService.getPanneById(panneId);
        if (panne != null) {
            suivi.setPanne(panne);
            return suiviDao.save(suivi);
        }
        return null;
    }

    public void deleteSuivi(int suiviId) {
        suiviDao.deleteById(suiviId);
    }

    public SuiviEntity updateSuivi(int suiviId, SuiviEntity suivi) {
        SuiviEntity suiviToUpdate = suiviDao.findById(suiviId).orElse(null);
        if (suiviToUpdate != null) {
//            suiviToUpdate.setStatut(suivi.getStatut());
            return suiviDao.save(suiviToUpdate);
        }
        return null;
    }

    public SuiviEntity editSuivi(int suiviId, SuiviEntity suivi) {
        SuiviEntity suiviToEdit = suiviDao.findById(suiviId).orElse(null);
        if (suiviToEdit != null) {
//            suiviToEdit.setStatut(suivi.getStatut());
            int panneId = suivi.getPanne().getPanneId();
            PanneEntity panne = panneService.getPanneById(panneId);
            if (panne != null) {
                suiviToEdit.setPanne(panne);
            }
            return suiviDao.save(suiviToEdit);
        }
        return null;
    }

    public void deleteAllSuivis() {
        suiviDao.deleteAll();
    }

//    public List<SuiviEntity> searchSuiviByStatut(String statut) {
//        return suiviDao.findByStatutContainingIgnoreCase(statut);
//    }
}
