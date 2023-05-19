package com.example.loctest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "gerer")
public class GererEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;


    @ManyToOne
    @JoinColumn(name = "materiel_id")
    private MaterielEntity materiel;

}
