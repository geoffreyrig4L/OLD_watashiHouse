package com.projetb3.api_watashihouse.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Utilisateur")
public class Utilisateur {

    //affichage par ID ne marche pas

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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
    private String type_user;

    //uni
    @OneToMany(
            cascade = CascadeType.MERGE,
            fetch = FetchType.EAGER)
    @JoinColumn(name = "id_utilisateur")
    Set<Commande> commandes = new HashSet<>();

    //FetchMode définit comment Hibernate va récupérer les données (par sélection, jointure ou sous-sélection). FetchType, d'autre part, définit si Hibernate chargera les données avec impatience ou paresseusement.

    //uni
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<CarteDePaiement> carteDePaiements = new ArrayList<>();


    public void add(CarteDePaiement carte) {
        carteDePaiements.add(carte);
    }
}