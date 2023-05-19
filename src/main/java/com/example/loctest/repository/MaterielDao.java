package com.example.loctest.repository;

import com.example.loctest.entity.MaterielEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterielDao extends CrudRepository<MaterielEntity, Integer> {

//    List<MaterielEntity> findByMarqueContainingIgnoreCase(String materielmarque);
}
