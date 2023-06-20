

package com.example.loctest.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "materiel")
public class MaterielEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "materiel_Id")
    private Integer materielId;

    @Column(name = "materiel_marque")
    private String materielMarque;

    @Column(name = "materiel_ref")
    private String materielRef;

    @Column(name = "materiel_des")
    private String materielDescription;


    @Column(name = "materiel_qte")
    private Integer materielQuantite;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeEntity type;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "loc_id")
    private LocalisationEntity localisation;

    @Column(name = "reserved")
    private boolean reserved;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "posseder",
            joinColumns = { @JoinColumn(name = "materiel_id") },
            inverseJoinColumns = { @JoinColumn(name = "doc_id") }
    )
    private Set<DocEntity> documents;

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
