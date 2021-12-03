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

    //bi
    @ManyToMany(
            fetch = FetchType.LAZY,         //permet de charger un article à la demande
            cascade = CascadeType.MERGE       //si modification d'une commande , la maj se fera aussi sur l'article
    )
    @JoinTable(
            name="Appartenir",  //on associe la table 'appartenir' qui resulte de la CIM
            joinColumns = @JoinColumn(name = "id_categorie"),
            inverseJoinColumns = @JoinColumn(name = "id_article")
    )
    private List<Article> articles = new ArrayList<>();
}
