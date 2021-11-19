package com.projetb3.webapp.service;
import com.projetb3.webapp.model.Utilisateur;
import com.projetb3.webapp.repository.UtilisateurProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data   //getter et setter
@Service //agit comme l'annot component + specifie que la classe gere le traitement métier
//Classe qui intègre des méthodes CRUD
//agit comme un relais, on peut dire quil agit comme le controller finalement

public class UtilisateurService {

    @Autowired //permet de réaliser l'injection automatiquement entre les beans de l'application
    private UtilisateurProxy articleProxy;    //récupère les données de la classe filmProxy

    //READ
    public Utilisateur getUtilisateur(final int id) {
        return articleProxy.getUtilisateur(id);
    }

    public Iterable<Utilisateur> getAllUtilisateurs(){        //iterabale declare que l'objet Utilisateur sera la cible d'une boucle for each afin de parcourir toutes les instances de Utilisateur
        return articleProxy.getAllUtilisateurs();
    }

    //DELETE
    public void deleteUtilisateur(final int id){
        articleProxy.deleteUtilisateur(id);
    }

    //CREATE
    public void createUtilisateur(Utilisateur u)  {
        articleProxy.createUtilisateur(u);
    }
}
