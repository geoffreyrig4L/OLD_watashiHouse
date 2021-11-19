package com.projetb3.webapp.service;
import com.projetb3.webapp.model.Commande;
import com.projetb3.webapp.repository.CommandeProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data   //getter et setter
@Service //agit comme l'annot component + specifie que la classe gere le traitement métier
//Classe qui intègre des méthodes CRUD
//agit comme un relais, on peut dire quil agit comme le controller finalement

public class CommandeService {

    @Autowired //permet de réaliser l'injection automatiquement entre les beans de l'application
    private CommandeProxy articleProxy;    //récupère les données de la classe filmProxy

    //READ
    public Commande getCommande(final int id) {
        return articleProxy.getCommande(id);
    }

    public Iterable<Commande> getAllCommandes(){        //iterabale declare que l'objet Commande sera la cible d'une boucle for each afin de parcourir toutes les instances de Commande
        return articleProxy.getAllCommandes();
    }

    //DELETE
    public void deleteCommande(final int id){
        articleProxy.deleteCommande(id);
    }

    //CREATE
    public void createCommande(Commande c)  {
        articleProxy.createCommande(c);
    }
}
