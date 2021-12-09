package com.projetb3.api_watashihouse.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="Article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nom")
    private String nom;

    @Column(name="description")
    private String description;

    //potentiel pb de type
    @Column(name="images")
    private String images;

    @Column(name="couleur")
    private String couleur;

    @Column(name="prix")
    private float prix;

    @Column(name="nb_avis")
    private int nb_avis;

    @Column(name="note")
    private int note;

    @Column(name="stock")
    private int stock;

    @ManyToMany(
            fetch = FetchType.EAGER,         //quand on recupere une article, on recupere la categorie
            cascade = CascadeType.MERGE       //si modification d'un article , la maj se fera aussi sur la categorie
    )
    @JoinTable(
            name="Appartenir",  //on associe la table 'appartenir' qui resulte de la CIM
            joinColumns = @JoinColumn(name = "id_article"),
            inverseJoinColumns = @JoinColumn(name = "id_categorie")
    )
    private List<Categorie> categories = new ArrayList<>();

    //uni
    @ManyToOne(
        cascade = CascadeType.ALL,  //toutes les actions sur l’entité article seront propagées sur l’entité collection
        fetch = FetchType.EAGER     //quand on recupere une article, on recupere une collection
    )
    @JoinColumn(name="id_collection")
    private Collection collection;

}
