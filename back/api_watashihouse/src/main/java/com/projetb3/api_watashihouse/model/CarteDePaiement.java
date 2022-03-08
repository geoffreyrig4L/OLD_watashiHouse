package com.projetb3.api_watashihouse.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @Column(name="anneeExpiration")
    private String anneeExpiration;

    @Column(name="moisExpiration")
    private String moisExpiration;

    @ManyToOne(
            cascade = CascadeType.MERGE,
            targetEntity=Utilisateur.class
    )
    @JoinColumn(name="id_utilisateur_carte", nullable = false)
    @JsonBackReference
    private Utilisateur utilisateur;
}
