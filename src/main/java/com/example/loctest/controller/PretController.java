//package com.example.loctest.controller;
//
//import java.util.List;
//
//import com.example.loctest.entity.PretEntity;
//import com.example.loctest.service.PretService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/demande_prets")
//public class PretController {
//
//    @Autowired
//    private PretService pretService;
//
//    @GetMapping("/demande_prets")
//    public List<PretEntity> getAllPrets() {
//        return pretService.getAllPrets();
//    }
//
//    @GetMapping("/{pretId}")
//    public PretEntity getPretById(@PathVariable int pretId) {
//        return pretService.getPretById(pretId);
//    }
//
//    @PostMapping("")
//    public PretEntity addPret(@RequestBody PretEntity pret) {
//        return pretService.addPret(pret);
//    }
//
//    @DeleteMapping("/{pretId}")
//    public ResponseEntity<String> deletePret(@PathVariable int pretId) {
//        PretEntity pret = pretService.getPretById(pretId);
//        if (pret == null) {
//            return new ResponseEntity<>("Pret not found", HttpStatus.NOT_FOUND);
//        }
//        pretService.deletePret(pretId);
//        return new ResponseEntity<>("Pret deleted successfully", HttpStatus.OK);
//    }
//}
//
//
//



import com.example.loctest.entity.PretEntity;
import com.example.loctest.service.PretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pret")
public class PretController {

    @Autowired
    private PretService pretService;

    // Récupérer tous les prêts
    @GetMapping("")
    public List<PretEntity> getAllPrets() {
        return pretService.getAllPrets();
    }

    // Récupérer un prêt par ID
    @GetMapping("/{pretId}")
    public PretEntity getPretById(@PathVariable int pretId) {
        return pretService.getPretById(pretId);
    }

    // Ajouter un nouveau prêt
    @PostMapping("/add")

    public ResponseEntity<PretEntity> addPret(@RequestBody PretEntity pret) {
        // Valider les données de l'utilisateur avant d'ajouter un nouveau prêt
        if (pret.getPretDescription() == null || pret.getPretDescription().isEmpty() || pret.getDateDebut() == null || pret.getDateFin() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PretEntity newPret = pretService.addPret(pret);
        return new ResponseEntity<>(newPret, HttpStatus.CREATED);
    }

    // Supprimer un prêt par ID
    @DeleteMapping("/{pretId}")
    public ResponseEntity<String> deletePret(@PathVariable int pretId) {
        PretEntity pret = pretService.getPretById(pretId);
        if (pret == null) {
            return new ResponseEntity<>("Pret not found", HttpStatus.NOT_FOUND);
        }
        pretService.deletePret(pretId);
        return new ResponseEntity<>("Pret deleted successfully", HttpStatus.OK);
    }
}
