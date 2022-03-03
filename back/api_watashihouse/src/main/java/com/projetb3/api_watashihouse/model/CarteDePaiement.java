package com.projetb3.api_watashihouse.model;


import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="carte_de_paiement")
public class CarteDePaiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="numero")
    private String numero;

    @Column(name="cvc")
    private String cvc;

    @Column(name="annee_expiration")
    private String annee_expiration;

    @Column(name="mois_expiration")
    private String mois_expiration;

    @Column(name="id_utilisateur")
    private int id_utilisateur;
}
