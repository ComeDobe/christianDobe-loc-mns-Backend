package com.example.loctest.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "materiel")
public class MaterielEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "materiel_Id")
    private Integer materielId;

    @Column(name = "materiel_marque")
    private String materielMarque;

    @Column(name = "materiel_ref")
    private String materielRef;

    @Column(name = "materiel_des")
    private String materielDescription;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeEntity type;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "loc_id")
    private LocalisationEntity localisation;


//    public boolean isReserved() {
//        return reserved;
//    }
//
//    public void setReserved(boolean b) {
//        this.reserved = reserved;
//    }
}