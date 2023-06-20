package com.example.loctest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class ReserveRequest {
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private  String  pretDescription;

    private Integer materielQuantite;

    // Getters et Setters
}
