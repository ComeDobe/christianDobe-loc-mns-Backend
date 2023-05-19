package com.example.loctest.controller;

import com.example.loctest.entity.SuiviEntity;
import com.example.loctest.service.SuiviService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suivi")
public class SuiviController {

    @Autowired
    private SuiviService suiviService;

    @GetMapping("")
    public List<SuiviEntity> getAllSuivis() {
        return suiviService.getAllSuivis();
    }

    @GetMapping("/{suiviId}")
    public SuiviEntity getSuiviById(@PathVariable int suiviId) {
        return suiviService.getSuiviById(suiviId);
    }

    @PostMapping("")
    public SuiviEntity addSuivi(@RequestBody SuiviEntity suivi) {
        return suiviService.addSuivi(suivi);
    }

    @DeleteMapping("/{suiviId}")
    public ResponseEntity<String> deleteSuivi(@PathVariable int suiviId) {
        suiviService.deleteSuivi(suiviId);
        return new ResponseEntity<>("Suivi has been deleted", HttpStatus.OK);
    }

    // Other mapping for update, delete and search


    @PutMapping("/{suiviId}")
    public SuiviEntity updateSuivi(@PathVariable int suiviId, @RequestBody SuiviEntity suivi) {
        return suiviService.updateSuivi(suiviId, suivi);
    }

    @PatchMapping("/{suiviId}")
    public SuiviEntity editSuivi(@PathVariable int suiviId, @RequestBody SuiviEntity suivi) {
        return suiviService.editSuivi(suiviId, suivi);
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteAllSuivis() {
        suiviService.deleteAllSuivis();
        return new ResponseEntity<>("All suivis have been deleted", HttpStatus.OK);
    }

//    @GetMapping("/search")
//    public List<SuiviEntity> searchSuiviByStatut(@RequestParam String statut) {
//        return suiviService.searchSuiviByStatut(statut);
//    }

}
