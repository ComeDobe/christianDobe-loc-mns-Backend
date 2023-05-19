package com.example.loctest.repository;

import com.example.loctest.entity.EtatEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtatDao extends CrudRepository<EtatEntity, Integer> {
    List<EtatEntity> findByEtatConditionContainingIgnoreCase(String condition);
}

