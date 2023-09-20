

package com.example.loctest.service;

import com.example.loctest.repository.RoleDao;
import com.example.loctest.repository.UserDao;
import com.example.loctest.entity.Role;
import com.example.loctest.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void initRoleAndUser() {
        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role");
        roleDao.save(userRole);

        User adminUser = new User();
        adminUser.setUserName("admin123");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));
        adminUser.setUserFirstName("admin");
        adminUser.setUserLastName("admin");
        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

        User user = new User();
        user.setUserName("christ123");
        user.setUserPassword(getEncodedPassword("christ@123"));
        user.setUserFirstName("christ");
        user.setUserLastName("dobe");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);
    }

    public User registerUserFromForm(User user) {
        if (userDao.findByUserName(user.getUserName()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (userDao.findByUserEmail(user.getUserEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (!isPasswordValid(user.getUserPassword())) {
            throw new IllegalArgumentException("Invalid password format");
        }

        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setUserPassword(getEncodedPassword(user.getUserPassword()));
        newUser.setUserFirstName(user.getUserFirstName());
        newUser.setUserLastName(user.getUserLastName());
        newUser.setUserEmail(user.getUserEmail());
        newUser.setUserAdresse(user.getUserAdresse());
        newUser.setUserMobile(user.getUserMobile());
        newUser.setUserDate(user.getUserDate());
        newUser.setUserCity(user.getUserCity());

        Optional<Role> userRole = roleDao.findByRoleName("User");
        if (userRole.isEmpty()) {
            throw new IllegalStateException("Default role 'User' not found");
        }
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole.get());
        newUser.setRole(userRoles);

        return userDao.save(newUser);
    }

//    public User addUser(User user) {
//        if (userDao.findByUserName(user.getUserName()).isPresent()) {
//            throw new IllegalArgumentException("Username already exists");
//        }
//        if (userDao.findByUserEmail(user.getUserEmail()).isPresent()) {
//            throw new IllegalArgumentException("Email already exists");
//        }
//
//        user.setUserPassword(getEncodedPassword(user.getUserPassword()));
//
//        return userDao.save(user);
//    }


    public User addUser(User user) {
        // Vérifier si le nom d'utilisateur existe déjà
        Optional<User> existingUser = userDao.findByUserName(user.getUserName());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        // Vérifier si l'e-mail existe déjà
        Optional<Object> existingEmail = userDao.findByUserEmail(user.getUserEmail());
        if (existingEmail.isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }
        // Encoder le mot de passe
        String encodedPassword = passwordEncoder.encode(user.getUserPassword());
        user.setUserPassword(encodedPassword);

        // Enregistrer l'utilisateur dans la base de données
        User addedUser = userDao.save(user);

        return addedUser;
    }


    public User updateUser(int userId, User user) {
        User existingUser = userDao.findById(String.valueOf(userId))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        existingUser.setUserName(user.getUserName());
        existingUser.setUserEmail(user.getUserEmail());
        existingUser.setUserFirstName(user.getUserFirstName());
        existingUser.setUserLastName(user.getUserLastName());

        return userDao.save(existingUser);
    }

    public void deleteUser(int userId) {
        User user = userDao.findById(String.valueOf(userId))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userDao.delete(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    public List<User> getAllUsers() {
        return (List<User>) userDao.findAll();
    }
    private boolean isPasswordValid(String password) {
        // Vérifier la longueur minimale du mot de passe
        if (password.length() >= 8) {
            return false;
        }
        // Vérifier la présence d'au moins un caractère spécial
        if (!password.matches(".*[!@#$%^&*()].*")) {
            return false;
        }
        // Vérifier la présence d'au moins un chiffre
        if (!password.matches(".*\\d.*")) {
            return false;
        }
        // Vérifier la présence d'au moins une majuscule
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        return true;
    }



//    public User getUser(String token) {
//        return users.get(token);
//    }
}







