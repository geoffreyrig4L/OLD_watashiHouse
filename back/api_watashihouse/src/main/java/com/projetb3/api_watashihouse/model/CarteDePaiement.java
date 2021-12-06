package com.projetb3.api_watashihouse.model;

import lombok.Data;
import org.hibernate.annotations.CollectionId;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="carte_de_paiement")
public class CarteDePaiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_carte;

    @Column(name="numero")
    private String numero;

    @Column(name="cvc")
    private int cvc;

    @Column(name="annee_expiration")
    private int annee_expiration;

    @Column(name="mois_expiration")
    private int mois_expiration;


}
