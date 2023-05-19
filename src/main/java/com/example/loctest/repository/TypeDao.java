package com.example.loctest.repository;

import com.example.loctest.entity.TypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeDao extends CrudRepository<TypeEntity, Integer> {

}

