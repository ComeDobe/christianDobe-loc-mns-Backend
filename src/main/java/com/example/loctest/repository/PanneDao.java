package com.example.loctest.repository;

import com.example.loctest.entity.PanneEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PanneDao extends CrudRepository<PanneEntity, Integer> {

}
