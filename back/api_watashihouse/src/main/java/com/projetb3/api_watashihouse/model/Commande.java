package com.projetb3.api_watashihouse.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="Commande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_commande")
    private int id_commande;

    @Column(name="numero")
    private String numero;

    //importation de date =~ probleme
    @Column(name="date_livraison")
    private Date date_livraison;

    @Column(name="prix_tot")
    private float prix_tot;

    @Column(name="lien_vers")
    private String lien_vers;

    //bidirectionnelle
    @ManyToOne(
            cascade = {
                    CascadeType.PERSIST, //si supression d'un user = on conserve la commande
                    CascadeType.MERGE //si modification d'un user , la maj se fera aussi dans la table commande
            },
            fetch = FetchType.LAZY     //quand on recupere un user, on recupere ses commandes
    )
    @JoinColumn(name = "id_utilisateur")    //association avec la clé étrangère
    private Utilisateur utilisateur;


    //unidirectionnelle
    @ManyToMany(
            fetch = FetchType.LAZY,  //permet de charger un article à la demande
            cascade = {
                    CascadeType.PERSIST,    //si creation ou modification d'une commande , la maj se fera aussi dans la table contenir
                    CascadeType.MERGE       // grace a merge aussi
            }
    )
    @JoinTable(
            name="Contenir",  //on associe la table 'contenir' qui resulte de la CIM
            joinColumns = @JoinColumn(name = "id_commande"),
            inverseJoinColumns = @JoinColumn(name = "id_article")
    )
    private List<Article> articles = new ArrayList<>();
}
