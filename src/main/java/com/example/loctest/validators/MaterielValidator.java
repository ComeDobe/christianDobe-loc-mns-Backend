package com.example.loctest.validators;


import com.example.loctest.entity.MaterielEntity;

public class MaterielValidator {

    public static boolean isMaterielMarqueValid(String marque) {
        return marque != null && !marque.trim().isEmpty();
    }

    public static boolean isMaterielRefValid(String reference) {
        return reference != null && !reference.trim().isEmpty();
    }

    public static boolean isMaterielEntityValid(MaterielEntity materiel) {
        return materiel != null
                && isMaterielMarqueValid(materiel.getMaterielMarque())
                && isMaterielRefValid(materiel.getMaterielRef())
                && materiel.getLocalisation() != null;
    }
}
