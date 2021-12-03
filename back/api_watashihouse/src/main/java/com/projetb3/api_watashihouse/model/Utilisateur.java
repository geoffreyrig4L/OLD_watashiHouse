package com.projetb3.api_watashihouse.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="Utilisateur")
public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_utilisateur")
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

    @Column(name = "code_postal")
    private String code_postal;

    //bidirectionnelle
    @OneToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "id_commande")    //association avec la clé étrangère
    private List<Commande> commandes = new ArrayList<>();

    //bidirectionnelle
    @OneToMany(
            cascade = CascadeType.ALL, //si suppression ou modification d'un user , la maj se fera aussi dans la table carte
            orphanRemoval = true,       //evite la presence d'une carte dont l user a été supprimé
            fetch = FetchType.EAGER     //quand on recupere un user, on recupere ses cartes
    )
    @JoinColumn(name = "id_carte")    //association avec la clé étrangère
    private List<CarteDePaiement> carteDePaiements = new ArrayList<>();

}
