package com.example.loctest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "pannes")
public class PanneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "panne_id")
    private int panneId;

    @Column(name = "panne_description")
    private String panneDescription;

    @Column(name = "user_name")
    private String userName;

    @ManyToOne
    private MaterielEntity materiel;

}
