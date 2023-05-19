//Avec methode de mise à jour , recherche et edition


package com.example.loctest.service;

import java.util.List;

import com.example.loctest.repository.MaterielDao;
import com.example.loctest.entity.MaterielEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MaterielService {

    @Autowired
    private MaterielDao materielDao;

    public List<MaterielEntity> getAllMateriels() {
        return (List<MaterielEntity>) materielDao.findAll();
    }

    public MaterielEntity getMaterielById(int materielId) {
        return materielDao.findById(materielId).orElse(null);
    }

    public MaterielEntity addMateriel(MaterielEntity materiel) {
        return materielDao.save(materiel);
    }

    public void deleteMateriel(int materielId) {
        materielDao.deleteById(materielId);
    }

    public MaterielEntity updateMateriel(MaterielEntity materiel) {
        MaterielEntity currentMateriel = materielDao.findById(materiel.getMaterielId()).orElse(null);
        if (currentMateriel != null) {
            currentMateriel.setMaterielMarque(materiel.getMaterielMarque());
            currentMateriel.setType(materiel.getType());
//            currentMateriel.setNumeroSerie(materiel.getNumeroSerie());
            currentMateriel.setLocalisation(materiel.getLocalisation());
//            currentMateriel.setEtat(materiel.getEtat());
            return materielDao.save(currentMateriel);
        }
        return null;
    }

    public MaterielEntity editMateriel(int materielId, MaterielEntity materiel) {
        MaterielEntity currentMateriel = materielDao.findById(materielId).orElse(null);
        if (currentMateriel != null) {
            if (materiel.getMaterielMarque() != null) {
                currentMateriel.setMaterielMarque(materiel.getMaterielMarque());
            }
            if (materiel.getType() != null) {
                currentMateriel.setType(materiel.getType());
            }
//            if (materiel.getNumeroSerie() != null) {
//                currentMateriel.setNumeroSerie(materiel.getNumeroSerie());
//            }
            if (materiel.getLocalisation() != null) {
                currentMateriel.setLocalisation(materiel.getLocalisation());
            }
//            if (materiel.getEtat() != null) {
//                currentMateriel.setEtat(materiel.getEtat());
//            }
            return materielDao.save(currentMateriel);
        }
        return null;
    }

//    public List<MaterielEntity> searchMaterielByMarque(String materielmarque) {
//        return materielDao.findByMarqueContainingIgnoreCase(materielmarque);
//    }
}


//Okkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk

//
//package com.example.loctest.service;
//
//
//import java.util.List;
//
//import com.example.loctest.dao.MaterielDao;
//import com.example.loctest.entity.MaterielEntity;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MaterielService {
//
//    @Autowired
//    private MaterielDao materielDao;
//
//    public List<MaterielEntity> getAllMateriels() {
//        return (List<MaterielEntity>) materielDao.findAll();
//    }
//
//    public MaterielEntity getMaterielById(int materielId) {
//        return materielDao.findById(materielId).orElse(null);
//    }
//
//    public MaterielEntity addMateriel(MaterielEntity materiel) {
//        return materielDao.save(materiel);
//    }
//
//    public void deleteMateriel(int materielId) {
//        materielDao.deleteById(materielId);
//    }
//
//    public void updateMateriel(MaterielEntity currentMateriel) {
//    }
//
////    public List<MaterielEntity> searchMaterielByMarque(String marque) {
////    }
//}