package com.example.loctest.repository;
import com.example.loctest.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends CrudRepository<Role, String> {

    Optional<Role> findByRoleName(String user);
}
