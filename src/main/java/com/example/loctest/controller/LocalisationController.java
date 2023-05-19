package com.example.loctest.controller;

import java.util.List;

import com.example.loctest.entity.LocalisationEntity;
import com.example.loctest.service.LocalisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/localisations")
public class LocalisationController {

    @Autowired
    private LocalisationService localisationService;

    @GetMapping("")
    public List<LocalisationEntity> getAllLocalisations() {
        return localisationService.getAllLocalisations();
    }

    @GetMapping("/{locId}")
    public LocalisationEntity getLocalisationById(@PathVariable int locId) {
        return localisationService.getLocalisationById(locId);
    }

    @PostMapping("/localisation")
    public LocalisationEntity addLocalisation(@RequestBody LocalisationEntity localisation) {
        return localisationService.addLocalisation(localisation);
    }

    @DeleteMapping("/{locId}")
    public ResponseEntity<String> deleteLocalisation(@PathVariable int locId) {
        LocalisationEntity localisation = localisationService.getLocalisationById(locId);
        if (localisation == null) {
            return new ResponseEntity<>("Localisation not found", HttpStatus.NOT_FOUND);
        }
        localisationService.deleteLocalisation(locId);
        return new ResponseEntity<>("Localisation deleted successfully", HttpStatus.OK);
    }
}
