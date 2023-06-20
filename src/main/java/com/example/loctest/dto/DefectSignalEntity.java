package com.example.loctest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefectSignalEntity {

    private Integer materielId;
    private Integer panneId;

    private String userName;
    private String materielDescription;
    private String imageUrl;
    private String panneDescription;
}
