package com.projetb3.api_watashihouse.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name="Utilisateur")
public class Utilisateur {

    //affichage par ID ne marche pas

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_utilisateur;

    @Column(name = "civilite")
    private String civilite;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "email")
    private String email;

    @Column(name = "mdp")
    private String mdp;

    @Column(name = "tel")
    private String tel;

    @Column(name = "adresse_livraison")
    private String adresse_livraison;

    @Column(name = "adresse_facturation")
    private String adresse_facturation;

    @Column(name = "pays")
    private String pays;

    @Column(name = "type_user")
    private String type;

    //uni
    @OneToMany(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "id_utilisateur")
    Set<Commande> commandes = new HashSet<>();

    //uni
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "id_utilisateur")
    Set<CarteDePaiement> carteDePaiements = new  HashSet<>();
}
