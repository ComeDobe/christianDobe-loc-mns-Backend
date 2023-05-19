package com.example.loctest;


import com.example.loctest.entity.User;
import com.example.loctest.security.WebSecurityConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class LocTestApplicationTests {

    @Test
    void contextLoads() {
    }
//
//    @Test
//    void creationUtilisateur_idUtilisateurNull() {
//        User utilisateur= new User();
//        assertNull(utilisateur.getUserName());
//    }
//
//    @Autowired
//    private WebApplicationContext context;
//    private MockMvc mvc;
//    @BeforeEach
//    public void setup() {
//        mvc = MockMvcBuilders
//                .webAppContextSetup(context)
//                .apply(springSecurity())
//                .build();
//    }
//
//    void appelUrlRacine_OKattendu() throws Exception {
//        mvc.perform(get("/")).andExpect(content().string("le serveur marche mais y rien ici"));
//    }
//
//
//    void utilisateurNonConnecteappelUrlUtilisateur_403attendu() throws Exception {
//        mvc.perform(get("/user")).andExpect(status().isForbidden());
//    }
//
//    @Test
//    @WithMockUser(roles = {"admin"})
//    void utilisateurConnecteappelUrlListeUtilisateur_OKattendu() throws Exception {
//        mvc.perform(get("/registerNewUser")).andExpect(status().isOk());
//    }
//
////
////    @Test
////    @WithMockUser(roles = {"FORADMIN"})
////    void administrateurConnecteappelUrlListeUtilisateur_OKattendu() throws Exception {
////        mvc.perform(get("/user")).andExpect(status().isOk());
////    }




}
