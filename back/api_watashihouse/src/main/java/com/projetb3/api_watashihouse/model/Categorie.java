package com.projetb3.api_watashihouse.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name="Categorie")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nom")
    private String nom;

    @JsonIgnore
    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            targetEntity=Article.class
    )
    @JoinTable(
            name = "Appartenir",
            joinColumns = @JoinColumn(name = "id_categorie"),
            inverseJoinColumns = @JoinColumn(name = "id_article")
    )
    private Set<Article> articles = new HashSet<>();
}
