package com.projetb3.api_watashihouse.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name="commande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="numero")
    private String numero;

    @Column(name="dateAchat")
    private String dateAchat;

    @Column(name="prixTot")
    private int prixTot;

    @ManyToOne(
            cascade = CascadeType.MERGE,
            targetEntity=Utilisateur.class
    )
    @JoinColumn(name="id_utilisateur_commande")
    @JsonBackReference
    private Utilisateur utilisateur;

    @ManyToMany(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name="Article_Commande",
            joinColumns = @JoinColumn(name = "id_commande"),
            inverseJoinColumns = @JoinColumn(name = "id_article")
    )
    private List<Article> articles = new ArrayList<>();

    public static String now(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        return formatter.format(date);
    }

    public void addAll(List<Article> articles){
        articles.addAll(articles);
    }

    public void remove(Article article){
        articles.remove(article);
    }
}
