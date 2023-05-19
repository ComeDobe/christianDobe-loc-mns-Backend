//package com.example.loctest.controller;
//
//import com.example.loctest.entity.UsureEntity;
//import com.example.loctest.service.UsureService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/usures")
//public class UsureController {
//
//    @Autowired
//    private UsureService usureService;
//
//    @GetMapping("")
//    public List<UsureEntity> getAllUsures() {
//        return usureService.getAllUsures();
//    }
//
//    @GetMapping("/{usureId}")
//    public UsureEntity getUsureById(@PathVariable int usureId) {
//        return usureService.getUsureById(usureId);
//    }
//
//    @PostMapping("")
//    public ResponseEntity<String> addUsure(@RequestBody UsureEntity usure) {
//        UsureEntity newUsure = usureService.addUsure(usure);
//        return new ResponseEntity<>("Usure created successfully with id " + newUsure.getMateriel(), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{usureId}")
//    public ResponseEntity<String> deleteUsure(@PathVariable int usureId) {
//        UsureEntity usure = usureService.getUsureById(usureId);
//        if (usure == null) {
//            return new ResponseEntity<>("Usure not found", HttpStatus.NOT_FOUND);
//        }
//        usureService.deleteUsure(usureId);
//        return new ResponseEntity<>("Usure deleted successfully", HttpStatus.OK);
//    }
//
//    @PutMapping("/{usureId}")
//    public ResponseEntity<String> updateUsure(@PathVariable int usureId, @RequestBody UsureEntity usure) {
//        UsureEntity currentUsure = usureService.getUsureById(usureId);
//        if (currentUsure == null) {
//            return new ResponseEntity<>("Usure not found", HttpStatus.NOT_FOUND);
//        }
//        currentUsure.setMateriel(usure.getMateriel());
//        currentUsure.setEtat(usure.getEtat());
//        currentUsure.setDateChangement(usure.getDateChangement());
//        usureService.updateUsure(currentUsure);
//        return new ResponseEntity<>("Usure updated successfully", HttpStatus.OK);
//    }
//
//    @PatchMapping("/{usureId}")
//    public ResponseEntity<String> editUsure(@PathVariable int usureId, @RequestBody UsureEntity usure) {
//        UsureEntity currentUsure = usureService.getUsureById(usureId);
//        if (currentUsure == null) {
//            return new ResponseEntity<>("Usure not found", HttpStatus.NOT_FOUND);
//        }
//        if (usure.getMateriel() != null) {
//            currentUsure.setMateriel(usure.getMateriel());
//        }
//        if (usure.getEtat() != null) {
//            currentUsure.setEtat(usure.getEtat());
//        }
//        if (usure.getDateChangement() != null) {
//            currentUsure.setDateChangement(usure.getDateChangement());
//        }
//        usureService.updateUsure(currentUsure);
//        return new ResponseEntity<>("Usure updated successfully", HttpStatus.OK);
//    }
////
////    @GetMapping("/search")
////    public List<UsureEntity> searchUsureByMaterielId(@RequestParam int materielId) {
////        return usureService.searchUsureByMaterielId(materielId);
////    }
//}
