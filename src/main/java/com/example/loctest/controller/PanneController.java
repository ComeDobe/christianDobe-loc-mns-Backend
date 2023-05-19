package com.example.loctest.controller;

import java.util.List;

import com.example.loctest.entity.PanneEntity;
import com.example.loctest.service.PanneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pannes")
public class PanneController {

    @Autowired
    private PanneService panneService;

    @GetMapping("")
    public List<PanneEntity> getAllPannes() {
        return panneService.getAllPannes();
    }

    @GetMapping("/{panneId}")
    public PanneEntity getPanneById(@PathVariable int panneId) {
        return panneService.getPanneById(panneId);
    }

    @PostMapping("")
    public PanneEntity addPanne(@RequestBody PanneEntity panne) {
        return panneService.addPanne(panne);
    }

    @DeleteMapping("/{panneId}")
    public ResponseEntity<String> deletePanne(@PathVariable int panneId) {
        PanneEntity panne = panneService.getPanneById(panneId);
        if (panne == null) {
            return new ResponseEntity<>("Panne not found", HttpStatus.NOT_FOUND);
        }
        panneService.deletePanne(panneId);
        return new ResponseEntity<>("Panne deleted successfully", HttpStatus.OK);
    }
}

