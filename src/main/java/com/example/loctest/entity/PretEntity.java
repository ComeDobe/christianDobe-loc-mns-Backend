package com.example.loctest.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pret")
@Getter
@Setter
public class PretEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pret_id")
    private int pretId;

    @Column(name = "pret_debut")
    private LocalDate dateDebut;

    @Column(name = "pret_fin")
    private LocalDate dateFin;

    @Column(name = "pret_description")
    private String pretDescription;

    @Column(name = "pret_qte")
    private Integer pretQuantite;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;

    @Column(name = "pret_valide")
    private boolean pretValide;

    @Column(name = "prolongation_valide")
    private boolean prolongationValide;



    public void setMateriel(MaterielEntity materiel) {
    }

    public MaterielEntity getMateriel() {
        return null;
    }

    public void setValide(boolean valide) {
        this.pretValide = valide;
    }

    public void setProlongationValide(boolean valide) {
        this.prolongationValide = valide;
    }


    public boolean isValide() {
        return  pretValide;
    }
}
