package com.example.loctest;


import com.example.loctest.dto.ReserveRequest;
import com.example.loctest.entity.MaterielEntity;
import com.example.loctest.entity.PretEntity;
import com.example.loctest.entity.Role;
import com.example.loctest.entity.User;
import com.example.loctest.repository.RoleDao;
import com.example.loctest.repository.UserDao;
import com.example.loctest.security.MonUserDetails;
import com.example.loctest.security.WebSecurityConfiguration;
import com.example.loctest.service.EmailService;
import com.example.loctest.service.MaterielService;
import com.example.loctest.service.PretService;
import com.example.loctest.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
class LocTestApplicationTests {

    @Mock
    private UserDao userDao;

    @Mock
    private RoleDao roleDao;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private MaterielService materielService;

    @Mock
    private UserDetailsService userDetailsService;

    @Mock
    private PretService pretService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private UserService userService;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    void testAddUser() {
        // Créer un utilisateur fictif
        User user = new User();
        user.setUserName("christian");
        user.setUserEmail("christdobe@test.com");
        user.setUserPassword("root");

        // Configurer le mock pour renvoyer une valeur vide, indiquant que l'utilisateur n'existe pas
        when(userDao.findByUserName("christian")).thenReturn(Optional.empty());
        when(userDao.findByUserEmail("christdobe@test.com")).thenReturn(Optional.empty());

        // Configurer le mock pour renvoyer une valeur encodée pour le mot de passe
        String encodedPassword = "encodedPassword";
        when(passwordEncoder.encode("root")).thenReturn(encodedPassword);

        // Configurer le mock pour renvoyer l'utilisateur ajouté
        User addedUser = new User();
        addedUser.setUserName("christian");
        addedUser.setUserEmail("christdobe@test.com");
        when(userDao.save(user)).thenReturn(addedUser);

        // Appeler la méthode à tester
        User result = userService.addUser(user);

        // Vérifier les assertions
        assertNotNull(result);
        assertEquals("christian", result.getUserName());
        assertEquals("christdobe@test.com", result.getUserEmail());

        // Vérifier que les méthodes du mock ont été appelées avec les bons arguments
        verify(userDao).findByUserName("christian");
        verify(userDao).findByUserEmail("christdobe@test.com");
        verify(passwordEncoder).encode("root");
        verify(userDao).save(user);
    }


//    @Test
//    void testReserveMateriel() {
//        // Créer les données de test
//        int materielId = 1;
//        ReserveRequest request = new ReserveRequest();
//        request.setDateDebut(LocalDate.now());
//        request.setDateFin(LocalDate.now().plusDays(7));
//        request.setPretDescription("Test reservation");
//        request.setMaterielQuantite(2);
//
//        // Créer un utilisateur fictif
//        User user = new User();
//        user.setUserName("testUser");
//        user.setUserEmail("test@example.com");
//        user.setUserFirstName("John");
//        user.setUserLastName("Doe");
//
//        // Créer un matériel fictif
//        MaterielEntity materiel = new MaterielEntity();
//        materiel.setMaterielId(materielId);
//        materiel.setMaterielMarque("Test Marque");
//        materiel.setMaterielRef("Test Ref");
//        materiel.setMaterielDescription("Test Description");
//        materiel.setMaterielQuantite(5);
//
//        // Configurer le mock pour la méthode getMaterielById
//        when(materielService.getMaterielById(materielId)).thenReturn(materiel);
//
//        // Configurer le mock pour la méthode loadUserByUsername
//        when(userDetailsService.loadUserByUsername(user.getUserName())).thenReturn(new MonUserDetails(user));
//
//        // Configurer le mock pour la méthode createPret
//        PretEntity pret = new PretEntity();
//        when(pretService.createPret(any(PretEntity.class))).thenReturn(pret);
//
//        // Appeler la méthode à tester
//        ResponseEntity<String> response = userService.reserveMateriel(materielId, request, authentication);
//
//        // Vérifier les assertions
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Materiel reserved successfully", response.getBody());
//
//        // Vérifier que la méthode getMaterielById a été appelée avec le bon ID de matériel
//        verify(materielService).getMaterielById(materielId);
//
//        // Vérifier que la méthode loadUserByUsername a été appelée avec le bon nom d'utilisateur
//        verify(userDetailsService).loadUserByUsername(user.getUserName());
//
//        // Vérifier que la méthode createPret a été appelée avec le bon argument
//        ArgumentCaptor<PretEntity> pretCaptor = ArgumentCaptor.forClass(PretEntity.class);
//        verify(pretService).createPret(pretCaptor.capture());
//        PretEntity capturedPret = pretCaptor.getValue();
//        assertEquals(user, capturedPret.getUser());
//        assertEquals(materiel, capturedPret.getMateriel());
//        assertEquals(request.getDateDebut(), capturedPret.getDateDebut());
//        assertEquals(request.getDateFin(), capturedPret.getDateFin());
//        assertEquals(request.getPretDescription(), capturedPret.getPretDescription());
//        assertEquals(request.getMaterielQuantite(), capturedPret.getPretQuantite());
//
//        // Vérifier que la méthode sendEmail a été appelée avec les bons arguments pour l'administrateur
//        verify(emailService).sendEmail(eq("christiandobe01@gmail.com"), eq("Réservation de matériel"), anyString());
//
//        // Vérifier que la méthode sendEmail a été appelée avec les bons arguments pour l'utilisateur
//        verify(emailService).sendEmail(eq(user.getUserEmail()), eq("Confirmation de réservation"), anyString());
//    }



    @Test
    void contextLoads() {

    }
}

