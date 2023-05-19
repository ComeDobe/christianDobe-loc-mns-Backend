package com.example.loctest.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "documents")
public class DocEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private int id;

    @Column(name = "doc_titre")
    private String titre;

    @Column(name = "doc_description")
    private String description;

    // Constructeurs, getters, setters
}
