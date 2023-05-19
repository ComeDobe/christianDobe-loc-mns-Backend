package com.example.loctest.service;

import java.util.List;

import com.example.loctest.repository.LocalisationDao;
import com.example.loctest.entity.LocalisationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocalisationService {

    @Autowired
    private LocalisationDao localisationDao;

    public List<LocalisationEntity> getAllLocalisations() {
        return (List<LocalisationEntity>) localisationDao.findAll();
    }

    public LocalisationEntity getLocalisationById(int locId) {
        return localisationDao.findById(locId).orElse(null);
    }

    public LocalisationEntity addLocalisation(LocalisationEntity localisation) {
        return localisationDao.save(localisation);
    }

    public void deleteLocalisation(int locId) {
        localisationDao.deleteById(locId);
    }
}
