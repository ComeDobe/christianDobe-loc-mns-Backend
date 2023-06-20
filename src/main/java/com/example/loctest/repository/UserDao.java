package com.example.loctest.repository;
import com.example.loctest.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<User, String> {
    Optional<User> findByUserName(String userName);

    Optional<Object> findByUserEmail(String userEmail);
}
