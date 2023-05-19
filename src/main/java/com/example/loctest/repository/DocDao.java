package com.example.loctest.repository;



import com.example.loctest.entity.DocEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocDao extends JpaRepository<DocEntity, Integer> {
}
