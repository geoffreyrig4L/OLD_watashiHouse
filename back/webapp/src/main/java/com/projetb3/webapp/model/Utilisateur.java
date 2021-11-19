package com.projetb3.webapp.model;

import lombok.Data;

@Data
public class Utilisateur{

    private Integer id;
    private String civilite;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String tel;
    private String adresse_livraison;
    private String adresse_facturation;
    private String code_postal;
    // private Enum type;
}
