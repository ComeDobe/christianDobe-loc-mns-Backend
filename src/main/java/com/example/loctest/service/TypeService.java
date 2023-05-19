package com.example.loctest.service;

import java.util.List;

import com.example.loctest.repository.TypeDao;
import com.example.loctest.entity.TypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeService {

    @Autowired
    private TypeDao typeDao;

    public List<TypeEntity> getAllTypes() {
        return (List<TypeEntity>) typeDao.findAll();
    }

    public TypeEntity getTypeById(int typeId) {
        return typeDao.findById(typeId).orElse(null);
    }

    public TypeEntity addType(TypeEntity type) {
        return typeDao.save(type);
    }

    public void deleteType(int typeId) {
        typeDao.deleteById(typeId);
    }

    public void updateType(TypeEntity currentType) {
    }
}
