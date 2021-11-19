package com.projetb3.webapp.model;

import lombok.Data;

import java.util.Date;

@Data
public class CarteDePaiement {
    private Integer id;
    private int numero;
    private int cvc;
    private Date date_expiration;
}
