package com.example.loctest.controller;

import com.example.loctest.entity.EtatEntity;
import com.example.loctest.service.EtatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/etats")
public class EtatController {

    @Autowired
    private EtatService etatService;

    @GetMapping("")
    public List<EtatEntity> getAllEtats() {
        return etatService.getAllEtats();
    }

    @GetMapping("/{etatId}")
    public EtatEntity getEtatById(@PathVariable int etatId) {
        return etatService.getEtatById(etatId);
    }

    @PostMapping("")
    public ResponseEntity<String> addEtat(@RequestBody EtatEntity etat) {
        EtatEntity newEtat = etatService.addEtat(etat);
        return new ResponseEntity<>("Etat created successfully with id " + newEtat.getEtatId(), HttpStatus.OK);
    }

    @DeleteMapping("/{etatId}")
    public ResponseEntity<String> deleteEtat(@PathVariable int etatId) {
        EtatEntity etat = etatService.getEtatById(etatId);
        if (etat == null) {
            return new ResponseEntity<>("Etat not found", HttpStatus.NOT_FOUND);
        }
        etatService.deleteEtat(etatId);
        return new ResponseEntity<>("Etat deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{etatId}")
    public ResponseEntity<String> updateEtat(@PathVariable int etatId, @RequestBody EtatEntity etat) {
        EtatEntity currentEtat = etatService.getEtatById(etatId);
        if (currentEtat == null) {
            return new ResponseEntity<>("Etat not found", HttpStatus.NOT_FOUND);
        }
        currentEtat.setEtatCondition(etat.getEtatCondition());
        etatService.updateEtat(currentEtat);
        return new ResponseEntity<>("Etat updated successfully", HttpStatus.OK);
    }

    @PatchMapping("/{etatId}")
    public ResponseEntity<String> editEtat(@PathVariable int etatId, @RequestBody EtatEntity etat) {
        EtatEntity currentEtat = etatService.getEtatById(etatId);
        if (currentEtat == null) {
            return new ResponseEntity<>("Etat not found", HttpStatus.NOT_FOUND);
        }
        if (etat.getEtatCondition() != null) {
            currentEtat.setEtatCondition(etat.getEtatCondition());
        }
        etatService.updateEtat(currentEtat);
        return new ResponseEntity<>("Etat edited successfully", HttpStatus.OK);
    }

    @GetMapping("/search")
    public List<EtatEntity> searchEtatByCondition(@RequestParam(name = "condition") String condition) {
        return etatService.searchEtatByCondition(condition);
    }
}
