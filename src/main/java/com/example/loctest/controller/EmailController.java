
//ce code fonctionne

//package com.example.loctest.controller;
//
//import com.example.loctest.dto.LoanRequest;
//import com.example.loctest.service.EmailService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Map;

//
//@RestController
//@RequiredArgsConstructor
//public class EmailController {
//
//    private final EmailService emailService;
//
//    @RequestMapping(value = "/send-email", method = RequestMethod.POST)
//    public String sendEmailToAdmin(@RequestBody Map<String, String> request) {
//        String adminEmail = "christiandobe01@gmail.com";
//        String subject = "Nouveau message d'un utilisateur";
//        String message = request.get("message");
//        emailService.sendEmail(adminEmail, subject, message);
//        return "success";
//    }
//}




package com.example.loctest.controller;

import com.example.loctest.dto.LoanRequest;
import com.example.loctest.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    @RequestMapping(value = "/send-email", method = RequestMethod.POST)
    public String sendEmailToAdmin(@RequestBody Map<String, String> request) {
        String adminEmail = "christiandobe01@gmail.com";
        String subject = "Nouveau message d'un utilisateur";
        String message = request.get("message");
        emailService.sendEmail(adminEmail, subject, message);
        return "success";
    }

    @PostMapping("/send-confirmation")
    public String sendConfirmationEmail(@RequestBody LoanRequest loanRequest) {
        String userEmail = loanRequest.getUserEmail();
        String subject = "Confirmation de demande de prêt de matériel";
        String message = "Cher utilisateur,\n\nVotre demande de prêt de matériel a été confirmée. Nous vous contacterons bientôt pour organiser les détails du prêt" +
                "\n\nCordialement,\nL'équipe de l'application de prêt de matériel.";
        emailService.sendEmail(userEmail, subject, message);
        return "success";
    }
}
