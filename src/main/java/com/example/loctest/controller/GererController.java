package com.example.loctest.controller;

import java.util.List;

import com.example.loctest.entity.GererEntity;
import com.example.loctest.service.GererService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gerers")
public class GererController {

    @Autowired
    private GererService gererService;

    @GetMapping("")
    public List<GererEntity> getAllGerers() {
        return gererService.getAllGerers();
    }

    @PostMapping("")
    public GererEntity addGerer(@RequestBody GererEntity gerer) {
        return gererService.addGerer(gerer);
    }

    @DeleteMapping("/{gererId}")
    public ResponseEntity<String> deleteGerer(@PathVariable int gererId) {
        GererEntity gerer = gererService.getGererById(gererId);
        if (gerer == null) {
            return new ResponseEntity<>("Gerer not found", HttpStatus.NOT_FOUND);
        }
        gererService.deleteGerer(gererId);
        return new ResponseEntity<>("Gerer deleted successfully", HttpStatus.OK);
    }
}

