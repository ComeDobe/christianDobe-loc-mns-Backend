package com.example.loctest.repository;

import com.example.loctest.entity.LocalisationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalisationDao extends CrudRepository<LocalisationEntity, Integer> {

}
