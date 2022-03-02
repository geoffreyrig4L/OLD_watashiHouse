package com.projetb3.api_watashihouse.model;

import com.projetb3.api_watashihouse.repository.UtilisateurRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.*;

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

    @ManyToOne
    //name = /nom dans la table user/, referencedColumnName=/nom de la FK dans carte/
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name="id_utilisateur", referencedColumnName="id", insertable = false, updatable = false)
    private Utilisateur utilisateur;
}
