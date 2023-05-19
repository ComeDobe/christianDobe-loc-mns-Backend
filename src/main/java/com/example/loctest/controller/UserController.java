//package com.example.loctest.controller;
//import com.example.loctest.entity.User;
//import com.example.loctest.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.PostConstruct;
//
//@RestController
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostConstruct
//    public void initRoleAndUser() {
//        userService.initRoleAndUser();
//    }
//
//    @PostMapping({"/registerNewUser"})
//    public User registerNewUser(@RequestBody User user) {
//        return userService.registerNewUser(user);
//    }
//
//    @GetMapping({"/forAdmin"})
//    @PreAuthorize("hasRole('Admin')")
//    public String forAdmin(){
//        return "cette page est reservée aux admin";
//    }
//
//    @GetMapping({"/forUser"})
//    @PreAuthorize("hasRole('User')")
//    public String forUser(){
//        return "Bienvenu sur la page d'utilisateur";
//    }
//}




//pour administrateur

package com.example.loctest.controller;

import com.example.loctest.entity.User;
import com.example.loctest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void initRoleAndUser() {
        userService.initRoleAndUser();
    }

    @PostMapping("")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            User newUser = userService.addUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/registerNewUser")
    public User registerNewUser(@RequestBody User user) {
        return userService.registerUserFromForm(user);
    }

//    @PostMapping("/addUser")
//    @PreAuthorize("hasRole('Admin')")
//    public User addUser(@RequestBody User user) {
//        return userService.addUser(user);
//    }

    @PutMapping("/updateUser/{userId}")
    @PreAuthorize("hasRole('Admin')")
    public User updateUser(@PathVariable int userId, @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    @DeleteMapping("/deleteUser/{userId}")
    @PreAuthorize("hasRole('Admin')")
    public void deleteUser(@PathVariable int userId) {
        userService.deleteUser(userId);
    }

    @GetMapping("/forAdmin")
    @PreAuthorize("hasRole('Admin')")
    public String forAdmin() {
        return "Cette page est réservée aux administrateurs";
    }

    @GetMapping("/forUser")
    @PreAuthorize("hasAnyRole('User','Admin')")
    public String forUser() {
        return "Bienvenue sur la page utilisateur";
    }
    @GetMapping("/users")
    @PreAuthorize("hasRole('Admin')")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}



//pour utilisateur j'ai remplacé admin par user


//
//
//package com.example.loctest.controller;
//
//import com.example.loctest.entity.User;
//import com.example.loctest.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//
//@RestController
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @PostConstruct
//    public void initRoleAndUser() {
//        userService.initRoleAndUser();
//    }
//
//    @PostMapping("")
//    public ResponseEntity<User> addUser(@RequestBody User user) {
//        try {
//            User newUser = userService.addUser(user);
//            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }
//
//
//    @PostMapping("/registerNewUser")
//    public User registerNewUser(@RequestBody User user) {
//        return userService.registerUserFromForm(user);
//    }
//
////    @PostMapping("/addUser")
////    @PreAuthorize("hasRole('Admin')")
////    public User addUser(@RequestBody User user) {
////        return userService.addUser(user);
////    }
//
//    @PutMapping("/updateUser/{userId}")
//    @PreAuthorize("hasRole('User')")
//    public User updateUser(@PathVariable int userId, @RequestBody User user) {
//        return userService.updateUser(userId, user);
//    }
//
//    @DeleteMapping("/deleteUser/{userId}")
//    @PreAuthorize("hasRole('User')")
//    public void deleteUser(@PathVariable int userId) {
//        userService.deleteUser(userId);
//    }
//
//    @GetMapping("/forUser")
//    @PreAuthorize("hasRole('User')")
//    public String forAdmin() {
//        return "Cette page est réservée aux administrateurs";
//    }
//
//    @GetMapping("/forUser")
//    @PreAuthorize("hasRole('User')")
//    public String forUser() {
//        return "Bienvenue sur la page utilisateur";
//    }
//    @GetMapping("/users")
//    @PreAuthorize("hasRole('User')")
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }
//}
