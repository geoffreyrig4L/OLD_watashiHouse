package com.projetb3.api_watashihouse.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name="Categorie")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_categorie")
    private int id_categorie;

    @Column(name="nom")
    private String nom;

    //bidirectionnelle
    @ManyToMany(
            fetch = FetchType.LAZY,  //permet de charger un article à la demande
            cascade = {
                    CascadeType.PERSIST,    //si creation ou modification d'une categorie , la maj se fera aussi dans la table article
                    CascadeType.MERGE       // grace a merge aussi
            }
    )
    @JoinTable(
            name="Appartenir",  //on associe la table 'appartenir' qui resulte de la CIM
            joinColumns = @JoinColumn(name = "id_categorie"),
            inverseJoinColumns = @JoinColumn(name = "id_article")
    )
    private List<Article> articles = new ArrayList<>();
}
