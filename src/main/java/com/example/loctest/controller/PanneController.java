
//ce code fonctionne

//package com.example.loctest.controller;
//
//import java.util.List;
//
//import com.example.loctest.entity.PanneEntity;
//import com.example.loctest.service.PanneService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/pannes")
//public class PanneController {
//
//    @Autowired
//    private PanneService panneService;
//
//    @GetMapping("")
//    public List<PanneEntity> getAllPannes() {
//        return panneService.getAllPannes();
//    }
//
//    @GetMapping("/{panneId}")
//    public PanneEntity getPanneById(@PathVariable int panneId) {
//        return panneService.getPanneById(panneId);
//    }
//
//    @PostMapping("")
//    public PanneEntity addPanne(@RequestBody PanneEntity panne) {
//        return panneService.addPanne(panne);
//    }
//
//    @DeleteMapping("/{panneId}")
//    public ResponseEntity<String> deletePanne(@PathVariable int panneId) {
//        PanneEntity panne = panneService.getPanneById(panneId);
//        if (panne == null) {
//            return new ResponseEntity<>("Panne not found", HttpStatus.NOT_FOUND);
//        }
//        panneService.deletePanne(panneId);
//        return new ResponseEntity<>("Panne deleted successfully", HttpStatus.OK);
//    }
//}
//


//
//package com.example.loctest.controller;
//
//import java.util.List;
//
//import com.example.loctest.entity.PanneEntity;
//import com.example.loctest.service.MaterielService;
//import com.example.loctest.service.PanneService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//
//@RestController
//@RequestMapping("/pannes")
//public class PanneController {
//
//    @Autowired
//    private PanneService panneService;
//
//    @Autowired
//    private MaterielService materielService;
//
//
//@Autowired
//private FileStorageService fileStorageService;
//
//    @GetMapping("")
//    public ResponseEntity<List<PanneEntity>> getAllPannes() {
//        return new ResponseEntity<>(panneService.getAllPannes(), HttpStatus.OK);
//    }
//
//    @GetMapping("/{panneId}")
//    public ResponseEntity<PanneEntity> getPanneById(@PathVariable int panneId) {
//        PanneEntity panne = panneService.getPanneById(panneId);
//        if (panne == null) {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(panne, HttpStatus.OK);
//    }
//
//    @PostMapping("")
//    public ResponseEntity<PanneEntity> addPanne(@RequestBody PanneEntity panne) {
//        if (panne.getMateriel() == null || panne.getMateriel().getMaterielId() == null || materielService.getMaterielById(panne.getMateriel().getMaterielId()) == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>(panneService.addPanne(panne), HttpStatus.CREATED);
//    }
//
//
//    @DeleteMapping("/{panneId}")
//    public ResponseEntity<String> deletePanne(@PathVariable int panneId) {
//        PanneEntity panne = panneService.getPanneById(panneId);
//        if (panne == null) {
//            return new ResponseEntity<>("Panne not found", HttpStatus.NOT_FOUND);
//        }
//        panneService.deletePanne(panneId);
//        return new ResponseEntity<>("Panne deleted successfully", HttpStatus.OK);
//    }
//
//    @PostMapping("/{id}/image")
//    public ResponseEntity<?> uploadImage(@PathVariable int  panneId, @RequestParam("file") MultipartFile file) {
//        try {
//            String fileName = fileStorageService.storeFile(file);
//            PanneEntity panne = panneService.getPanneById(panneId);
//            if (panne == null) {
//                return new ResponseEntity<>("Panne not found", HttpStatus.NOT_FOUND);
//            }
//            panne.setImageUrl(fileName);
//            panneService.savePanne(panne);
//            return new ResponseEntity<>("Image uploaded successfully", HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//}




package com.example.loctest.controller;

import java.io.IOException;
import java.util.List;

import com.example.loctest.dto.DefectSignalEntity;
import com.example.loctest.entity.MaterielEntity;
import com.example.loctest.entity.PanneEntity;
import com.example.loctest.entity.User;
import com.example.loctest.security.MonUserDetails;
import com.example.loctest.security.MonUserDetailsService;
import com.example.loctest.service.MaterielService;
import com.example.loctest.service.PanneService;
import com.example.loctest.service.strategy.FileStorageService; // Be sure to import this
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/pannes")
@CrossOrigin
public class PanneController {

    @Autowired
    private PanneService panneService;

    @Autowired
    private MaterielService materielService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private MonUserDetailsService userDetailsService;

    @GetMapping("")
    public ResponseEntity<List<PanneEntity>> getAllPannes() {
        return new ResponseEntity<>(panneService.getAllPannes(), HttpStatus.OK);
    }

    @GetMapping("/{panneId}")
    public ResponseEntity<PanneEntity> getPanneById(@PathVariable int  panneId) {
        PanneEntity panne = panneService.getPanneById(panneId);
        if (panne == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(panne, HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<PanneEntity> addPanne(@RequestBody PanneEntity panne, Authentication authentication) throws IOException {
        if (panne.getMateriel() == null || panne.getMateriel().getMaterielId() == null || materielService.getMaterielById(panne.getMateriel().getMaterielId()) == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // Récupérer les informations de l'utilisateur connecté
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = ((MonUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername())).getUser();

        // Attribuer l'utilisateur à la panne
        panne.setUserName(user.getUserName());

        return new ResponseEntity<>(panneService.addPanne(panne), HttpStatus.CREATED);
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

    @PostMapping("/{panneId}/image")
    public ResponseEntity<?> uploadImage(@PathVariable int panneId, @RequestParam("file") MultipartFile file) {
        try {
            String fileName = fileStorageService.storeFile(file);
            PanneEntity panne = panneService.getPanneById(panneId);
            if (panne == null) {
                return new ResponseEntity<>("Panne not found", HttpStatus.NOT_FOUND);
            }
            panne.setImageUrl(fileName);
            panneService.savePanne(panne);
            return new ResponseEntity<>("Document uploaded successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PostMapping("/signal")
//    public ResponseEntity<PanneEntity> signalDefect(@RequestPart("defect") DefectSignalEntity defect, @RequestPart("file") MultipartFile file) {
//        MaterielEntity materiel = materielService.getMaterielById(defect.getMaterielId());
//        if (materiel == null) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//        PanneEntity panne = new PanneEntity();
//        // Set up the new PanneEntity with details from the defect and the Materiel
//        panne.setMateriel(materiel);
//        // Save the image and set the imageURL in the PanneEntity
//        try {
//            String fileName = fileStorageService.storeFile(file);
//            panne.setImageUrl(fileName);
//        } catch (Exception e) {
//            e.printStackTrace();
//            PanneEntity errorPanne = new PanneEntity();
//            errorPanne.setErrorMessage(e.getMessage());
//            return new ResponseEntity<>(errorPanne, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//        // Set the description from the defect in the PanneEntity
//        panne.setPanneDescription(defect.getPanneDescription());
//
//        // ... any other setup, such as setting a default status, recording the time, etc.
//        return new ResponseEntity<>(panneService.addPanne(panne), HttpStatus.CREATED);
//    }

    @PostMapping("/signal")
    public ResponseEntity<PanneEntity> signalDefect(@RequestParam("file") MultipartFile file, @RequestPart("defect") DefectSignalEntity defect) {
        if (defect == null || file == null || materielService == null || fileStorageService == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Integer materielId = defect.getMaterielId();
        if (materielId == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        MaterielEntity materiel = materielService.getMaterielById(materielId);
        if (materiel == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        PanneEntity panne = new PanneEntity();
        panne.setMateriel(materiel);
        try {
            String fileName = fileStorageService.storeFile(file);
            panne.setImageUrl(fileName);
            panne.setPanneDescription(defect.getPanneDescription());
//            panneService.savePanne(panne); // Enregistrement de la panne dans la base de données
            panneService.addPanne(panne); // Enregistrement de la panne dans la base de données

            return new ResponseEntity<>(panne, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            PanneEntity errorPanne = new PanneEntity();
            errorPanne.setErrorMessage(e.getMessage());
            return new ResponseEntity<>(errorPanne, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
