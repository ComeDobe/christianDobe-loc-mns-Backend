package com.example.loctest.service;

import java.util.List;

import com.example.loctest.repository.PretDao;
import com.example.loctest.entity.PretEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PretService {

    @Autowired
    private PretDao pretDao;

    public List<PretEntity> getAllPrets() {
        return (List<PretEntity>) pretDao.findAll();
    }

    public PretEntity getPretById(int pretId) {
        return pretDao.findById(pretId).orElse(null);
    }

    public PretEntity addPret(PretEntity pret) {
        return pretDao.save(pret);
    }

    public void deletePret(int pretId) {
        pretDao.deleteById(pretId);
    }
}



// à verifier pour les validations
//

//
//package com.example.loctest.service;
//
//import java.util.List;
//import com.example.loctest.entity.PretEntity;
//import com.example.loctest.entity.MaterielEntity;
//import com.example.loctest.repository.PretDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class PretService {
//
//    @Autowired
//    private PretDao pretDao;
//
//    @Autowired
//    private MaterielService materielService;
//
//    public List<PretEntity> getAllPrets() {
//        return (List<PretEntity>) pretDao.findAll();
//    }
//
//    public PretEntity getPretById(int pretId) {
//        return pretDao.findById(pretId).orElse(null);
//    }
//
//    public PretEntity addPret(PretEntity pret) {
//        return pretDao.save(pret);
//    }
//
//    public void deletePret(int pretId) {
//        pretDao.deleteById(pretId);
//    }
//
//    public boolean validerPret(PretEntity pret) {
//        // Implémentez ici vos règles de validation pour le prêt
//        // Par exemple, vérifiez si le matériel est disponible, si les dates sont valides, etc.
//        // Renvoyez true si le prêt est valide, sinon renvoyez false
//
//        // Exemple de règle de validation : Vérifier si le matériel est disponible
//        MaterielEntity materiel = materielService.getMaterielById(pret.getMaterielId());
//        if (materiel == null || materiel.isReserved()) {
//            return false;
//        }
//
//        // Ajoutez d'autres règles de validation ici
//
//        return true;
//    }
//
//    public boolean reserverMateriel(int materielId) {
//        MaterielEntity materiel = materielService.getMaterielById(materielId);
//        if (materiel != null && !materiel.isReserved()) {
//            materiel.setReserved(true);
//            materielService.updateMateriel(materiel);
//            return true;
//        }
//        return false;
//    }
//}
