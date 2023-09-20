

package com.example.loctest.controller;

import com.example.loctest.dto.ExtendLoanRequest;
import com.example.loctest.dto.ReserveRequest;
import com.example.loctest.entity.MaterielEntity;
import com.example.loctest.entity.PretEntity;
import com.example.loctest.entity.User;
import com.example.loctest.security.MonUserDetails;
import com.example.loctest.security.MonUserDetailsService;
import com.example.loctest.service.EmailService;
import com.example.loctest.service.MaterielService;
import com.example.loctest.service.PretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/materiels")
public class MaterielController {

    @Autowired
    private MaterielService materielService;
    @Autowired
    private EmailService emailService;

    @Autowired
    private MonUserDetailsService userDetailsService;

    @Autowired
    private PretService pretService;

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
        currentMateriel.setMaterielQuantite(materiel.getMaterielQuantite());
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
        if (materiel.getMaterielQuantite() != null) {
            currentMateriel.setMaterielQuantite(materiel.getMaterielQuantite());
        }
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

    @PostMapping("/{materielId}/reserve")

    public ResponseEntity<String> reserveMateriel(@PathVariable int materielId, @RequestBody ReserveRequest dates, Authentication authentication) {

        LocalDate dateDebut = dates.getDateDebut();
        LocalDate dateFin = dates.getDateFin();
        String pretDescription = dates.getPretDescription();
        Integer materielQuantite = dates.getMaterielQuantite(); // Récupération de la quantité à réserver


        MaterielEntity materiel = materielService.getMaterielById(materielId);
        if (materiel == null) {
            return new ResponseEntity<>("Materiel not found", HttpStatus.NOT_FOUND);
        }

        // Ajouter une vérification pour s'assurer que le matériel est disponible
        if (materiel.getMaterielQuantite() < materielQuantite) {
            return new ResponseEntity<>("Insufficient materiel quantity available", HttpStatus.BAD_REQUEST);
        }

//        if (materiel.isReserved()) {
//            return new ResponseEntity<>("Materiel is already reserved", HttpStatus.BAD_REQUEST);
//        }

        materiel.setReserved(true);

        // Décrémenter la quantité de matériel disponible
        materiel.setMaterielQuantite(materiel.getMaterielQuantite() - materielQuantite);

//        if (materiel.getMaterielQuantite() > 0) {
//            materiel.setReserved(false);
//        } else {
//            materiel.setReserved(true);
//        }
        materiel.setReserved(materiel.getMaterielQuantite() <= 0);
        materielService.updateMateriel(materiel);


        // Récupérer les informations de l'utilisateur connecté
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = ((MonUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername())).getUser();
        String nomUtilisateur = user.getUserFirstName();
        String prenomUtilisateur = user.getUserLastName();
        String loginUtilisateur = String.valueOf(user.getUserName());
        String emailUtilisateur = user.getUserEmail();


        // Créer une nouvelle demande de reservation dans la table 'Pret'
        PretEntity pret = new PretEntity();
        pret.setUser(user);
        pret.setMateriel(materiel);
        pret.setDateDebut(dateDebut);
        pret.setDateFin(dateFin);
        pret.setPretDescription(pretDescription);
        pret.setPretQuantite(materielQuantite);



        pretService.createPret(pret);


        // Envoyer l'e-mail de confirmation avec les informations de l'utilisateur

        String adminEmail = "autorisation.loc@gmail.com"; // Remplacez par l'adresse e-mail de l'administrateur
        String subjectAdmin = "Réservation de matériel";
        String messageAdmin = "Une nouvelle demande de réservation\n\n" +
                "Utilisateur : " + nomUtilisateur + "\n" +
                "Prénom : " + prenomUtilisateur + "\n" +
                "Login : " + loginUtilisateur + "\n\n" +
                "Détails du matériel :\n" +
                "Ientifiant : " + materiel.getMaterielId() + "\n" +
                "Marque : " + materiel.getMaterielMarque() + "\n" +
                "Référence : " + materiel.getMaterielRef() + "\n" +
                "Quantité Reservée : " + pret.getPretQuantite()+ "\n" +
                "Description : " + materiel.getMaterielDescription() + "\n"+
                "Debut de pret :" + pret.getDateDebut() + "\n"+
                "Fin de pret :" + pret.getDateFin() + "\n"+
                "Description du pret  :" + pret.getPretDescription() + "\n";


        emailService.sendEmail(adminEmail, subjectAdmin, messageAdmin);

        // Envoyer l'e-mail de confirmation à l'utilisateur
        String subjectUser = "Confirmation de réservation";
        String messageUser = "Cher utilisateur,\n\nVotre demande de prêt de matériel a été confirmée. " +
                "Nous vous contacterons bientôt pour organiser les détails du prêt." +
                "\n\nDétails du matériel :\n" +
                "Identifiant du Materiel : " + materiel.getMaterielId()+ "\n" +
                "Identifiant du Pret : " + pret.getPretId()+ "\n" +
                "Référence : " + materiel.getMaterielRef() + "\n" +
                "Description : " + materiel.getMaterielDescription() + "\n";

        emailService.sendEmail(emailUtilisateur, subjectUser, messageUser);

        return new ResponseEntity<>("Materiel reserved successfully", HttpStatus.OK);
    }


    @PatchMapping("/{materielId}/pret/{pretId}/prolonger")
    public ResponseEntity<String> prolongerPret(@PathVariable int materielId, @PathVariable int pretId, @RequestBody ExtendLoanRequest extendRequest, Authentication authentication) {
        // Récupérer le prêt
        PretEntity pret = pretService.getPretById(pretId);
        if (pret == null) {
            return new ResponseEntity<>("Pret not found", HttpStatus.NOT_FOUND);
        }

        // Vérifiez si la nouvelle date de fin est après la date de fin actuelle
        LocalDate nouvelleDateFin = extendRequest.getNouvelleDateFin();
        String pretDescription = extendRequest.getPretDescription();

        if (nouvelleDateFin.isBefore(pret.getDateFin()) || nouvelleDateFin.isEqual(pret.getDateFin())) {
            return new ResponseEntity<>("New end date must be after current end date", HttpStatus.BAD_REQUEST);
        }

        // Vérifiez si le matériel est disponible pour la prolongation
        MaterielEntity materiel = materielService.getMaterielById(materielId);
        if (materiel == null || materiel.getMaterielQuantite() < 1) {
            return new ResponseEntity<>("Materiel not available for extension", HttpStatus.BAD_REQUEST);
        }

        // Vérifiez si l'utilisateur qui prolonge est le même utilisateur qui a réservé
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = ((MonUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername())).getUser();
        if (!pret.getUser().equals(user)) {
            return new ResponseEntity<>("Only the original borrower can extend the loan", HttpStatus.FORBIDDEN);
        }

        // Prolonger le prêt
        pret.setDateFin(nouvelleDateFin);
        pret.setPretDescription(pretDescription);
        pretService.updatePret(pret);

        // Envoyer un email de confirmation à l'utilisateur
        String emailUtilisateur = user.getUserEmail();

        String subject = "Confirmation de la prolongation du prêt";
        String message = "Cher utilisateur,\n\nVotre prêt de matériel a été prolongé jusqu'au " + nouvelleDateFin + ".\n\nDétails du matériel :\n" +
                "Marque : " + materiel.getMaterielMarque() + "\n" +
                "Référence : " + materiel.getMaterielRef() + "\n" +
                "Description : " + materiel.getMaterielDescription() + "\n";

        emailService.sendEmail(emailUtilisateur, subject, message);

        return new ResponseEntity<>("Pret extended successfully", HttpStatus.OK);
    }



    // Méthode pour valider une réservation de matériel par l'administrateur
    @PatchMapping("/pret/{pretId}/valider")
    public ResponseEntity<String> validerReservation(@PathVariable int pretId) {
        PretEntity pret = pretService.getPretById(pretId);
        if (pret == null) {
            return new ResponseEntity<>("Pret not found", HttpStatus.NOT_FOUND);
        }

        if (pret.isValide()) {
            return new ResponseEntity<>("Reservation already validated", HttpStatus.BAD_REQUEST);
        }
        pret.setValide(true); // Marquer la réservation comme validée
        pretService.updatePret(pret);

        // Envoyer un email de confirmation à l'utilisateur
        String emailUtilisateur = pret.getUser().getUserEmail();

        String subject = "Confirmation de validation de la réservation";
        String message = "Cher utilisateur,\n\nVotre réservation de matériel a été validée par l'administrateur.\n\nDétails du matériel :\n";
        // Vérifier si le matériel est présent dans la réservation
        MaterielEntity materiel = pret.getMateriel();
        if (materiel != null) {
            message += "Marque : " + materiel.getMaterielMarque() + "\n" +
                    "Référence : " + materiel.getMaterielRef() + "\n" +
                    "Description : " + materiel.getMaterielDescription() + "\n";
        } else {
            message += "Aucun détail de matériel disponible.\n";
        }

        emailService.sendEmail(emailUtilisateur, subject, message);

        return new ResponseEntity<>("Reservation validated successfully", HttpStatus.OK);
    }


    // Méthode pour valider une prolongation de prêt par l'administrateur
    @PatchMapping("/pret/{pretId}/prolongation/valider")
    public ResponseEntity<String> validerProlongation(@PathVariable int pretId) {
        PretEntity pret = pretService.getPretById(pretId);
        if (pret == null) {
            return new ResponseEntity<>("Pret not found", HttpStatus.NOT_FOUND);
        }

        if (pret.isProlongationValide()) {
            return new ResponseEntity<>("Prolongation already validated", HttpStatus.BAD_REQUEST);
        }
        pret.setProlongationValide(true); // Marquer la prolongation comme validée
        pretService.updatePret(pret);
        // Envoyer un email de confirmation à l'utilisateur
        String emailUtilisateur = pret.getUser().getUserEmail();
        String subject = "Confirmation de validation de la prolongation de prêt";
        String message = "Cher utilisateur,\n\nVotre prolongation de prêt de matériel a été validée par l'administrateur.\n\n";
        // Vérifier si le matériel est présent dans la réservation
        MaterielEntity materiel = pret.getMateriel();
        if (materiel != null) {
            message += "Détails du matériel :\n" +
                    "Marque : " + materiel.getMaterielMarque() + "\n" +
                    "Référence : " + materiel.getMaterielRef() + "\n" +
                    "Description : " + materiel.getMaterielDescription() + "\n";
        } else {
            message += "Aucun détail de matériel disponible.\n";
        }
        emailService.sendEmail(emailUtilisateur, subject, message);
        return new ResponseEntity<>("Prolongation validated successfully", HttpStatus.OK);
    }
    @GetMapping("/prets/en-attente")
    public List<PretEntity> getPendingPrets() {
        return pretService.getPendingPrets();
    }
}


//    la suppression est une option cette partie du code est fonctionnelle


//    @DeleteMapping("/{materielId}/reserve")
//    public ResponseEntity<String> unreserveMateriel(@PathVariable int materielId, Authentication authentication) {
//        // Récupération du matériel
//        MaterielEntity materiel = materielService.getMaterielById(materielId);
//        if (materiel == null) {
//            return new ResponseEntity<>("Matériel not found", HttpStatus.NOT_FOUND);
//        }
//        if (!materiel.isReserved()) {
//            return new ResponseEntity<>("Matériel is not reserved", HttpStatus.BAD_REQUEST);
//        }
//
//        // Récupérer les informations de l'utilisateur connecté
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        User user = ((MonUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername())).getUser();
//
//        // Vérifier que l'utilisateur connecté est le même qui a réservé le matériel
//        PretEntity pret = pretService.getPretByUserAndMateriel(user, materiel);
//        if (pret == null) {
//            return new ResponseEntity<>("Reservation not found for the current user", HttpStatus.BAD_REQUEST);
//        }
//
//        // Annulation de la réservation
//        pretService.deletePret(pret.getPretId());
//        materiel.setReserved(false);
//        materielService.updateMateriel(materiel);
//
//        return new ResponseEntity<>("Matériel unreserved successfully", HttpStatus.OK);
//    }

//}






























//    @PostMapping("/{materielId}/reserve")
//    public ResponseEntity<String> reserveMateriel(@PathVariable int materielId, Authentication authentication) {
//
//        LocalDate dateDebut = dates.getDateDebut();
//        LocalDate dateFin = dates.getDateFin();
//
//        MaterielEntity materiel = materielService.getMaterielById(materielId);
//        if (materiel == null) {
//            return new ResponseEntity<>("Materiel not found", HttpStatus.NOT_FOUND);
//        }
//        if (materiel.isReserved()) {
//            return new ResponseEntity<>("Materiel is already reserved", HttpStatus.BAD_REQUEST);
//        }
//        materiel.setReserved(true);
//        materielService.updateMateriel(materiel);
//
//        // Récupérer les informations de l'utilisateur connecté
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        User user = ((MonUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername())).getUser();
//        String nomUtilisateur = user.getUserFirstName();
//        String prenomUtilisateur = user.getUserLastName();
//        String loginUtilisateur = user.getUserName();
//        String emailUtilisateur = user.getUserEmail();
//
//
//        // Créer une nouvelle entrée dans la table 'Pret'
//        PretEntity pret = new PretEntity();
//        pret.setUser(user);
//        pret.setMateriel(materiel);
//
//        LocalDate dateDebut = LocalDate.now(); // Aujourd'hui comme date de début
//        LocalDate dateFin = dateDebut.plusDays(7); // Une semaine plus tard comme date de fin
//        pret.setDateDebut(dateDebut);
//        pret.setDateFin(dateFin);
//
//        pretService.createPret(pret);
//
//
//        // Envoyer l'e-mail de confirmation avec les informations de l'utilisateur
//        String adminEmail = "christiandobe01@gmail.com"; // Remplacez par l'adresse e-mail de l'administrateur
//        String subjectAdmin = "Réservation de matériel";
//        String messageAdmin = "Une nouvelle demande de réservation\n\n" +
//                "Utilisateur : " + nomUtilisateur + "\n" +
//                "Prénom : " + prenomUtilisateur + "\n" +
//                "Login : " + loginUtilisateur + "\n\n" +
//                "Détails du matériel :\n" +
//                "ID : " + materiel.getMaterielId() + "\n" +
//                "Marque : " + materiel.getMaterielMarque() + "\n" +
//                "Référence : " + materiel.getMaterielRef() + "\n" +
//                "Description : " + materiel.getMaterielDescription() + "\n";
//
//        emailService.sendEmail(adminEmail, subjectAdmin, messageAdmin);
//
//        // Envoyer l'e-mail de confirmation à l'utilisateur
//        String subjectUser = "Confirmation de réservation";
//        String messageUser = "Cher utilisateur,\n\nVotre demande de prêt de matériel a été confirmée. " +
//                "Nous vous contacterons bientôt pour organiser les détails du prêt." +
//                "\n\nDétails du matériel :\n" +
//                "Marque : " + materiel.getMaterielMarque() + "\n" +
//                "Référence : " + materiel.getMaterielRef() + "\n" +
//                "Description : " + materiel.getMaterielDescription() + "\n";
//
//        emailService.sendEmail(emailUtilisateur, subjectUser, messageUser);
//
//        return new ResponseEntity<>("Materiel reserved successfully", HttpStatus.OK);
//    }






//    @PostMapping("/{materielId}/reserve")
//    public ResponseEntity<String> reserveMateriel(@PathVariable int materielId, Authentication authentication) {
//
//        MaterielEntity materiel = materielService.getMaterielById(materielId);
//        if (materiel == null) {
//            return new ResponseEntity<>("Materiel not found", HttpStatus.NOT_FOUND);
//        }
//        if (materiel.isReserved()) {
//            return new ResponseEntity<>("Materiel is already reserved", HttpStatus.BAD_REQUEST);
//        }
//        materiel.setReserved(true);
//        materielService.updateMateriel(materiel);
//
//        // Récupérer les informations de l'utilisateur connecté
//
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        User user = ((MonUserDetails) userDetailsService.loadUserByUsername(userDetails.getUsername())).getUser();
//        String nomUtilisateur = user.getUserFirstName();
//        String prenomUtilisateur = user.getUserLastName();
//        String loginUtilisateur = user.getUserName();
//        String emailUtilisateur = user.getUserEmail();
//
//        // Envoyer l'e-mail de confirmation avec les informations de l'utilisateur
//        String adminEmail = "christiandobe01@gmail.com"; // Remplacez par l'adresse e-mail de l'administrateur
//        String subjectAdmin = "Réservation de matériel";
//        String messageAdmin = "Une nouvelle demande de réservation\n\n" +
//                "Utilisateur : " + nomUtilisateur + "\n" +
//                "Prénom : " + prenomUtilisateur + "\n" +
//                "Login : " + loginUtilisateur;
//
////        emailService.sendEmail(adminEmail, subject, message);
//        emailService.sendEmail(adminEmail, subjectAdmin, messageAdmin);
//
//        // Envoyer l'e-mail de confirmation à l'utilisateur
//
//        String subjectUser = "Confirmation de réservation";
//        String messageUser = "Cher utilisateur,\n\nVotre demande de prêt de matériel a été confirmée. " +
//                "Nous vous contacterons bientôt pour organiser les détails du prêt." +
//                "\n\nCordialement,\nL'équipe de l'application de prêt de matériel.";
//
//        emailService.sendEmail(emailUtilisateur, subjectUser, messageUser);
//
//
//
//        return new ResponseEntity<>("Materiel reserved successfully", HttpStatus.OK);
//    }
//
//}











//    @PostMapping("/{materielId}/reserve")
//    public ResponseEntity<String> reserveMateriel(@PathVariable int materielId) {
//        MaterielEntity materiel = materielService.getMaterielById(materielId);
//        if (materiel == null) {
//            return new ResponseEntity<>("Materiel not found", HttpStatus.NOT_FOUND);
//        }
//        if (materiel.isReserved()) {
//            return new ResponseEntity<>("Materiel is already reserved", HttpStatus.BAD_REQUEST);
//        }
//        materiel.setReserved(true);
//        materielService.updateMateriel(materiel);
//
//        // Envoyer l'e-mail de confirmation
//        String adminEmail = "christiandobe01@gmail.com"; // Remplacez par l'adresse e-mail de l'administrateur
//        String subject = "Réservation de matériel";
//        String message = "Une nouvelle demande de reservation";
//
//        emailService.sendEmail(adminEmail, subject, message);
//        return new ResponseEntity<>("Materiel reserved successfully", HttpStatus.OK);
//    }
//}






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
