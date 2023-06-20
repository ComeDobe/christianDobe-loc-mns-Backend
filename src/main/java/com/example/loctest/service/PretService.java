



package com.example.loctest.service;

import java.util.ArrayList;
import java.util.List;
import com.example.loctest.entity.PretEntity;
import com.example.loctest.entity.MaterielEntity;
import com.example.loctest.entity.User;
import com.example.loctest.repository.PretDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;




@Service
public class PretService {

    @Autowired
    private PretDao pretDao;

    @Autowired
    private MaterielService materielService;

    public List<PretEntity> getAllPrets() {
        return (List<PretEntity>) pretDao.findAll();
    }

    public PretEntity getPretById(int pretId) {
        return pretDao.findById(pretId).orElse(null);
    }

    public PretEntity addPret(PretEntity pret) {
        boolean isValid = validerPret(pret);
        if (isValid) {
            reserverMateriel(pret.getMateriel().getMaterielId());
            return pretDao.save(pret);
        }
        return null;
    }

    public void deletePret(int pretId) {
        pretDao.deleteById(pretId);
    }

    public boolean validerPret(PretEntity pret) {
        MaterielEntity materiel = pret.getMateriel();

        String materielRef = materiel.getMaterielRef();
        String materielMarque = materiel.getMaterielMarque();
        if (materielRef == null || materielRef.isEmpty() || materielMarque == null || materielMarque.isEmpty()) {
            return false;
        }
        // Vérifier si le matériel est disponible
        if (materiel.isReserved()) {
            return false;
        }
        // Ajoutez d'autres règles de validation ici
        pret.setValide(true);
        updatePret(pret);

        return true;
    }

    public boolean validerProlongation(int pretId) {
        PretEntity pret = pretDao.findById(pretId).orElse(null);
        if (pret == null) {
            return new ResponseEntity<>("Pret not found", HttpStatus.NOT_FOUND).hasBody();
        }

        if (pret.isProlongationValide()) {
            return new ResponseEntity<>("Prolongation already validated", HttpStatus.BAD_REQUEST).hasBody();
        }

        pret.setProlongationValide(true); // Marquer la prolongation comme validée

        updatePret(pret); // Mettre à jour le prêt dans la base de données


        return new ResponseEntity<>("Prolongation validated successfully", HttpStatus.OK).hasBody();
    }



    public boolean reserverMateriel(int materielId) {
        MaterielEntity materiel = materielService.getMaterielById(materielId);
        if (materiel != null && !materiel.isReserved()) {
            materiel.setReserved(true);
            materielService.updateMateriel(materiel);
            return true;
        }
        return false;
    }



    public void createPret(PretEntity pret) {
        pretDao.save(pret);
    }


    public PretEntity updatePret(PretEntity pretEntity) {
        return pretDao.save(pretEntity);
    }

//    public PretEntity getPretByUserAndMateriel(User user, MaterielEntity materiel) {
//        return pretDao.findByUserAndMateriel(user, materiel);
//    }



    public List<PretEntity> getPendingPrets() {
        List<PretEntity> allPrets = (List<PretEntity>) pretDao.findAll(); // Assuming you have a PretRepository or equivalent to access the prêt data
        List<PretEntity> pendingPrets = new ArrayList<>();

        for (PretEntity pret : allPrets) {
            if (!pret.isValide()) {
                pendingPrets.add(pret);
            }
        }

        return pendingPrets;
    }


}


