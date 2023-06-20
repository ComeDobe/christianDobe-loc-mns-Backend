package com.example.loctest.service.strategy;

import com.example.loctest.entity.PretEntity;
import com.example.loctest.entity.User;
import com.example.loctest.security.MonUserDetailsService;
import com.example.loctest.service.EmailService;
import com.example.loctest.service.PretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlertService {

    @Autowired
    private PretService pretService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private MonUserDetailsService userDetailsService;

    // Ce service s'exécute tous les jours à 12h00
    @Scheduled(cron = "0 0 12 * * ?")
//    @Scheduled(cron = "* * * * * ?")

    public void sendLateLoanAlerts() {
        List<PretEntity> allLoans = pretService.getAllPrets();
        LocalDate today = LocalDate.now();

        for (PretEntity pret : allLoans) {
            if (pret.getDateFin().isBefore(today)) {
                // Le prêt est en retard
                // Envoyer un email d'alerte à l'utilisateur
                User user = pret.getUser();
                UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
                String email = userDetails.getUsername();

                String subject = "Alerte de retard de prêt";
                String message = "Cher utilisateur,\n\nVotre prêt de matériel est en retard. " +
                        "Merci de nous retourner le matériel dès que possible.\n\nDétails du matériel :\n" +
                        "Référence : " + pret.getMateriel().getMaterielRef() + "\n" +
                        "Description : " + pret.getMateriel().getMaterielDescription() + "\n";

                emailService.sendEmail(email, subject, message);
            }
        }
    }
}
