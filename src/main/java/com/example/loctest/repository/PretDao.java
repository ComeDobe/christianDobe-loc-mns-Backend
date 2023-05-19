package com.example.loctest.repository;

import com.example.loctest.entity.PretEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PretDao extends CrudRepository<PretEntity, Integer> {

}
