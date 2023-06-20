package com.example.loctest.repository;

import com.example.loctest.entity.MaterielEntity;
import com.example.loctest.entity.PretEntity;
import com.example.loctest.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PretDao extends CrudRepository<PretEntity, Integer> {

//    PretEntity findByUserAndMateriel(User user, MaterielEntity materiel);

}
