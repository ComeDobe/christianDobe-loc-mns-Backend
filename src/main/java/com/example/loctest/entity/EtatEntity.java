package com.example.loctest.entity;



import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "etat_materiel")
public class EtatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "etat_id")
    private int etatId;

    @Column(name = "etat_condition")
    private String etatCondition;

    // Constructeurs, getters, setters
}

