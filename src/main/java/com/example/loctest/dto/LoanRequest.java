package com.example.loctest.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class LoanRequest {

    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userEmail;
    private String userMobile;
    private String materielMarque;
    private String materielRef;
    private String materielDescription;
    private Date dateDebut;
    private Date dateFin;
    private String typeLibelle;

}
