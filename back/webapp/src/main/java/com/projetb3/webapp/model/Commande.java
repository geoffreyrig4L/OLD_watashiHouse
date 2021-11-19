package com.projetb3.webapp.model;

import lombok.Data;

import java.util.Date;

@Data
public class Commande {
    private Integer id;
    private String numero;
    private Date date_livraison;
    private Float prix_tot;
    private String lien_vers;
}
