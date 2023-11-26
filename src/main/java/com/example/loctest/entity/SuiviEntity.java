package com.example.loctest.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "suivi")
public class SuiviEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int suiviId;

    @Column(name = "suivi_statut")
    private String suiviStatut;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "panne_id", referencedColumnName = "panne_id")
    private PanneEntity panne;

    public SuiviEntity() {
    }

    public SuiviEntity(String suiviStatut, PanneEntity panne) {
        this.suiviStatut = suiviStatut;
        this.panne = panne;
    }

    // Getters and setters
}

