package com.example.loctest.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "localisation")
public class LocalisationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loc_id")
    private int locId;

    @Column(name = "loc_lieu")
    @NotNull
    private String locLieu;

    @Column(name = "loc_batiment")
    @NotNull
    private String locBatiment;

    @Column(name = "loc_salle")
    @NotNull
    private String locSalle;



    public int getLocId() {
        return locId;
    }

    public void setLocId(int locId) {
        this.locId = locId;
    }

    public String getLocLieu() {
        return locLieu;
    }

    public void setLocLieu(String locLieu) {
        this.locLieu = locLieu;
    }
}
