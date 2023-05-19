package com.example.loctest.repository;

import com.example.loctest.entity.GererEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GererDao extends CrudRepository<GererEntity, Integer> {

    GererEntity getById(int gererId);
}
