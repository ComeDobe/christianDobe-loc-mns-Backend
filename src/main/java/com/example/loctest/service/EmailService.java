
//ce code fonctionne

//package com.example.loctest.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EmailService {
//
//    private JavaMailSender javaMailSender;
//
//    private String email;
//
//
//    @Autowired
//    public EmailService(JavaMailSender javaMailSender) {
//        this.javaMailSender = javaMailSender;
//    }
//
//    public void sendEmail(String to, String subject, String text) {
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setFrom(email);
//        message.setTo(to);
//        message.setSubject(subject);
//        message.setText(text);
//        javaMailSender.send(message);
//    }
//}
//
//
//


package com.example.loctest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
public class EmailService {

    private JavaMailSender javaMailSender;

    @Value("${admin.email}")
    private String adminEmail;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendConfirmationEmail(String userName, String userEmail) {
        String subject = "Confirmation de demande de prêt de matériel";
        String message = "Une nouvelle demande de prêt de matériel a été effectuée par l'utilisateur : \n" +
                "Nom : " + userName + "\n" +
                "Adresse e-mail : " + userEmail;

        sendEmail(adminEmail, subject, message);
    }

    public void sendEmail(String to, String subject, String text) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setFrom(adminEmail);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Une erreur s'est produite lors de l'envoi de l'e-mail", e);
        }
    }

}





//Cette partie du code permet de renvoyer un mail de confirmation à l'utilisateur


//import org.springframework.beans.factory.annotation.Value;
//        import org.springframework.stereotype.Service;
//
//        import javax.mail.*;
//        import javax.mail.internet.InternetAddress;
//        import javax.mail.internet.MimeMessage;
//        import java.util.Properties;

//@Service
//public class EmailService {
//
//    @Value("${spring.mail.host}")
//    private String host;
//
//    @Value("${spring.mail.port}")
//    private int port;
//
//    @Value("${spring.mail.username}")
//    private String username;
//
//    @Value("${spring.mail.password}")
//    private String password;
//
//    public void sendEmail(String recipient, String subject, String messageContent) {
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", host);
//        props.put("mail.smtp.port", port);
//
//        Session session = Session.getInstance(props, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(username));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
//            message.setSubject(subject);
//            message.setText(messageContent);
//
//            Transport.send(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
//}
