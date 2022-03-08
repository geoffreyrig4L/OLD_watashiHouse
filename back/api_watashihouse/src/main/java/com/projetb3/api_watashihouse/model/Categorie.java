package com.projetb3.api_watashihouse.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name="Categorie")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="nomCategorie")
    private String nom;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            targetEntity=Article.class
    )
    @JoinTable(
            name = "Article_Categorie",
            joinColumns = @JoinColumn(name = "id_categorie"),
            inverseJoinColumns = @JoinColumn(name = "id_article")
    )
    private Set<Article> articles = new HashSet<>();
}
