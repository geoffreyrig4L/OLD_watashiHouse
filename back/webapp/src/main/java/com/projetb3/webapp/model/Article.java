package com.projetb3.webapp.model;

import lombok.Data;

@Data
public class Article {

    private Integer id;
    private String nom;
    private String description;
    private String images;
    private String couleur;
    private float prix;
    private int nbAvis;
    private int note;
    private int stock;
}
