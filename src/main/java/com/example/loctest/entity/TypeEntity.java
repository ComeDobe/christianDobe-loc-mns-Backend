package com.example.loctest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "type")
public class TypeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private int typeId;


    @Column(name = "type_libelle")
    private String typeLibelle;

    @Column(name = "date_retour_reel")
    private Date dateRetourReel;


    @ManyToOne
    @JoinColumn(name = "pret_id")
    private PretEntity pret;






}
