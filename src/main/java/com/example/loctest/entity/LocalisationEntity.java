package com.example.loctest.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "localisation")
public class LocalisationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "loc_id")
    private int locId;

    @Column(name = "loc_lieu")
    @NotNull
    private String locLieu;

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
