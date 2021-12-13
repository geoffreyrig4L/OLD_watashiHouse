package com.projetb3.api_watashihouse.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="Commande")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="numero")
    private String numero;

    @Column(name="date_livraison")
    private Date date_livraison;

    @Column(name="prix_tot")
    private float prix_tot;

    @Column(name="lien_vers")
    private String lien_vers;

    //uni
    @ManyToMany(
            fetch = FetchType.LAZY,  //permet de charger un article à la demande
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @JoinTable(
            name="Contenir",  //on associe la table 'contenir' qui resulte de la CIM
            joinColumns = @JoinColumn(name = "id_commande"),
            inverseJoinColumns = @JoinColumn(name = "id_article")
    )
    private List<Article> articles = new ArrayList<>();

    public Date parseDate_livraison(String date_livraison) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date formattedDate = new Date();
        try {
            formattedDate = formatter.parse(date_livraison.replace("T", " "));
        }catch (Exception e){
            e.printStackTrace();
        }
        return formattedDate;
    }

}
