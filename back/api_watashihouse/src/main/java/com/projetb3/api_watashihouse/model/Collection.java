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

    //bi
    @OneToMany(
            mappedBy = "collection",
            orphanRemoval = true,       //garantit la non existence d article orphelin de sa collection
            cascade = CascadeType.ALL,  //toutes les actions sur l’entité collection seront propagées sur l’entité article
            fetch = FetchType.EAGER     //quand on recupere une collection, on recupere les articles
    )
    private List<Article> articles = new ArrayList<>();

}
