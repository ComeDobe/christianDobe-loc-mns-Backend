//package com.example.loctest.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//
//@Getter
//@Setter
//@Entity
//@Table(name = "usure")
//public class UsureEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//
//    @ManyToOne
//    @JoinColumn(name = "materiel_id")
//    private MaterielEntity materiel;
//
//    @ManyToOne
//    @JoinColumn(name = "etat_id")
//    private EtatEntity etat;
//
//    @Column(name = "date_changement")
//    private String dateChangement;
//
//    // Constructeurs, getters, setters
//}
