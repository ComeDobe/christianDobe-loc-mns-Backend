// Methode avec edition mise à jour et recherche

package com.example.loctest.controller;

import com.example.loctest.entity.MaterielEntity;
import com.example.loctest.service.MaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materiels")
public class MaterielController {

    @Autowired
    private MaterielService materielService;

    @GetMapping("")
    public List<MaterielEntity> getAllMateriels() {
        return materielService.getAllMateriels();
    }

    @GetMapping("/{materielId}")
    public MaterielEntity getMaterielById(@PathVariable int materielId) {
        return materielService.getMaterielById(materielId);
    }

    @PostMapping("")
    public ResponseEntity<String> addMateriel(@RequestBody MaterielEntity materiel) {
        MaterielEntity newMateriel = materielService.addMateriel(materiel);
        return new ResponseEntity<>("Materiel created successfully with id " + newMateriel.getMaterielId(), HttpStatus.OK);
    }

    @PutMapping("/{materielId}")
    public ResponseEntity<String> updateMateriel(@PathVariable int materielId, @RequestBody MaterielEntity materiel) {
        MaterielEntity currentMateriel = materielService.getMaterielById(materielId);
        if (currentMateriel == null) {
            return new ResponseEntity<>("Materiel not found", HttpStatus.NOT_FOUND);
        }
        currentMateriel.setMaterielMarque(materiel.getMaterielMarque());
        currentMateriel.setMaterielRef(materiel.getMaterielRef());
        currentMateriel.setMaterielDescription(materiel.getMaterielDescription());
//        currentMateriel.setQuantite(materiel.getQuantite());
        materielService.updateMateriel(currentMateriel);
        return new ResponseEntity<>("Materiel updated successfully", HttpStatus.OK);
    }

    @PatchMapping("/{materielId}")
    public ResponseEntity<String> editMateriel(@PathVariable int materielId, @RequestBody MaterielEntity materiel) {
        MaterielEntity currentMateriel = materielService.getMaterielById(materielId);
        if (currentMateriel == null) {
            return new ResponseEntity<>("Materiel not found", HttpStatus.NOT_FOUND);
        }
        if (materiel.getMaterielMarque() != null) {
            currentMateriel.setMaterielMarque(materiel.getMaterielMarque());
        }
        if (materiel.getType() != null) {
            currentMateriel.setType(materiel.getType());
        }
        if (materiel.getMaterielDescription() != null) {
            currentMateriel.setMaterielDescription(materiel.getMaterielDescription());
        }
//        if (materiel.getQuantite() != null) {
//            currentMateriel.setQuantite(materiel.getQuantite());
//        }
        materielService.updateMateriel(currentMateriel);
        return new ResponseEntity<>("Materiel updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{materielId}")
    public ResponseEntity<String> deleteMateriel(@PathVariable int materielId) {
        MaterielEntity materiel = materielService.getMaterielById(materielId);
        if (materiel == null) {
            return new ResponseEntity<>("Materiel not found", HttpStatus.NOT_FOUND);
        }
        materielService.deleteMateriel(materielId);
        return new ResponseEntity<>("Materiel deleted successfully", HttpStatus.OK);
    }
//
//    @GetMapping("/search")
//    public List<MaterielEntity> searchMaterielByMarque(@RequestParam("marque") String marque) {
//        return materielService.searchMaterielByMarque(marque);
//    }

}



//  okkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk

//package com.example.loctest.controller;
//import java.util.List;
//
//import com.example.loctest.entity.MaterielEntity;
//import com.example.loctest.service.MaterielService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/materiels")
//public class MaterielController {
//
//    @Autowired
//    private MaterielService materielService;
//
//    @GetMapping("")
//    public List<MaterielEntity> getAllMateriels() {
//        return materielService.getAllMateriels();
//    }
//
//    @GetMapping("/{materielId}")
//    public MaterielEntity getMaterielById(@PathVariable int materielId) {
//        return materielService.getMaterielById(materielId);
//    }
//
////    @PostMapping("/")
////    public MaterielEntity addMateriel(@RequestBody MaterielEntity materiel) {
////        return materielService.addMateriel(materiel);
////    }
//
//    @PostMapping("")
//    public ResponseEntity<String> addMateriel(@RequestBody MaterielEntity materiel) {
//        MaterielEntity newMateriel = materielService.addMateriel(materiel);
//        return new ResponseEntity<>("Materiel created successfully with id " + newMateriel.getMaterielId(), HttpStatus.OK);
//    }
//
//
//    @DeleteMapping("/{materielId}")
//    public ResponseEntity<String> deleteMateriel(@PathVariable int materielId) {
//        MaterielEntity materiel = materielService.getMaterielById(materielId);
//        if (materiel == null) {
//            return new ResponseEntity<>("Materiel not found", HttpStatus.NOT_FOUND);
//        }
//        materielService.deleteMateriel(materielId);
//        return new ResponseEntity<>("Materiel deleted successfully", HttpStatus.OK);
//    }
//
//}





//Deuxieme methode ***************************************


//
//package com.example.loctest.controller;
//
//import java.util.List;
//
//import com.example.loctest.entity.MaterielEntity;
//import com.example.loctest.service.MaterielService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/materiels")
//public class MaterielController {
//
//    @Autowired
//    private MaterielService materielService;
//
//    @GetMapping("")
//    public List<MaterielEntity> getAllMateriels() {
//        return materielService.getAllMateriels();
//    }
//
//    @GetMapping("/{materielId}")
//    public MaterielEntity getMaterielById(@PathVariable int materielId) {
//        return materielService.getMaterielById(materielId);
//    }
//
//    @PostMapping("")
//    public ResponseEntity<String> addMateriel(@RequestBody MaterielEntity materiel) {
//        MaterielEntity newMateriel = materielService.addMateriel(materiel);
//        return new ResponseEntity<>("Materiel created successfully with id " + newMateriel.getMaterielId(), HttpStatus.OK);
//    }
//
//    @DeleteMapping("/{materielId}")
//    public ResponseEntity<String> deleteMateriel(@PathVariable int materielId) {
//        MaterielEntity materiel = materielService.getMaterielById(materielId);
//        if (materiel == null) {
//            return new ResponseEntity<>("Materiel not found", HttpStatus.NOT_FOUND);
//        }
//        materielService.deleteMateriel(materielId);
//        return new ResponseEntity<>("Materiel deleted successfully", HttpStatus.OK);
//    }
//}




//cette partie est à verifier


//
//package com.example.loctest.controller;
//
//        import java.util.List;
//
//        import com.example.loctest.entity.MaterielEntity;
//        import com.example.loctest.service.MaterielService;
//        import org.springframework.beans.factory.annotation.Autowired;
//        import org.springframework.http.HttpStatus;
//        import org.springframework.http.ResponseEntity;
//        import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/materiels")
//public class MaterielController {
//
//    @Autowired
//    private MaterielService materielService;
//
//    @GetMapping("")
//    public List<MaterielEntity> getAllMateriels() {
//        return materielService.getAllMateriels();
//    }
//
//    @GetMapping("/{materielId}")
//    public MaterielEntity getMaterielById(@PathVariable int materielId) {
//        return materielService.getMaterielById(materielId);
//    }
//
//    @PostMapping("")
//    public MaterielEntity addMateriel(@RequestBody MaterielEntity materiel) {
//        return materielService.addMateriel(materiel);
//    }
//
//    @DeleteMapping("/{materielId}")
//    public ResponseEntity<String> deleteMateriel(@PathVariable int materielId) {
//        MaterielEntity materiel = materielService.getMaterielById(materielId);
//        if (materiel == null) {
//            return new ResponseEntity<>("Materiel not found", HttpStatus.NOT_FOUND);
//        }
//        materielService.deleteMateriel(materielId);
//        return new ResponseEntity<>("Materiel deleted successfully", HttpStatus.OK);
//    }
//
//    @PostMapping("/admin")
//    public ResponseEntity<String> addMaterielAsAdmin(@RequestBody MaterielEntity materiel) {
//        if (!isAdmin()) {
//            return new ResponseEntity<>("Unauthorized access", HttpStatus.UNAUTHORIZED);
//        }
//        MaterielEntity newMateriel = materielService.addMateriel(materiel);
//        return new ResponseEntity<>("Materiel created successfully with id " + newMateriel.getId(), HttpStatus.OK);
//    }
//
//    private boolean isAdmin() {
//        // Vérifiez si l'utilisateur connecté a les privilèges d'administrateur
//        // Vous pouvez utiliser Spring Security pour implémenter cette fonctionnalité
//    }
//}
