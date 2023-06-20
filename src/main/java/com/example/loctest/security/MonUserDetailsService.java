//package com.example.loctest.security;
//
//import com.example.loctest.entity.User;
//import com.example.loctest.repository.UserDao;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Service;
//import java.util.Optional;
//
//
//
//@Service
//public class MonUserDetailsService implements UserDetailsService {
//
//    private final UserDao userDao;
//
//    public MonUserDetailsService(UserDao userDao) {
//        this.userDao = userDao;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        Optional<User> optional = userDao.findByUserName(userName);
////        if (optional.isEmpty()) {
////            throw new UsernameNotFoundException("User not found");
////        }
////        return new MonUserDetails(optional.get());
////    }
//
//
//
//        if (User== null) {
//            throw new UsernameNotFoundException("Utilisateur introuvable: " + userName);
//        }
//
//        // Convertir l'utilisateur en une instance de MonUserDetails
//        return new MonUserDetails(User);
//    }
//}
//
//
//



package com.example.loctest.security;

import com.example.loctest.entity.User;
import com.example.loctest.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
//import org.springframework.security.core.userdetails.User;
import java.util.Optional;

@Service
public class MonUserDetailsService implements UserDetailsService {

    private final UserDao userDao;

    public MonUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> optional = userDao.findByUserName(userName);
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        User user = optional.get();
        return new MonUserDetails(user);
    }
}
