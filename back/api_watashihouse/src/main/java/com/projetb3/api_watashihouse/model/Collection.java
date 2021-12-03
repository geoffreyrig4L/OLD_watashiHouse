package com.projetb3.api_watashihouse.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="Collection")
public class Collection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_collection")
    private int id_collection;

    @Column(name="nom")
    private String nom;

    //bidirectionnelle
    @OneToMany(
            cascade = CascadeType.ALL, //si suppression ou modification d'un article , la maj se fera ici également
            orphanRemoval = true,       //evite la presence d'un article dont la collection a été supprimé
            fetch = FetchType.EAGER     //quand on recupere une collection, on recupere l'article
    )
    @JoinColumn(name = "id_article")    //association avec la clé étrangère
    private List<Article> articles = new ArrayList<>();


}
