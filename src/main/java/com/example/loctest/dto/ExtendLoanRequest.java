package com.example.loctest.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ExtendLoanRequest {
    private LocalDate nouvelleDateFin;
    private String pretDescription;

    // getters et setters
}
