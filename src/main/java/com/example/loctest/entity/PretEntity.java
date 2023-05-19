package com.example.loctest.entity;


import com.example.loctest.entity.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "demande_pret")
@Getter
@Setter
public class PretEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pret_id")
    private int pretId;

    @Column(name = "pret_debut")
    private Date dateDebut;

    @Column(name = "pret_fin")
    private Date dateFin;

    @Column(name = "pret_description")
    private String pretDescription;

    @ManyToOne
    @JoinColumn(name = "user_name")
    private User user;

//    public int getMaterielId() {
//    }
}
