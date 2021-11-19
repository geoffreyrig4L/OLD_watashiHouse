package com.projetb3.webapp.service;
import com.projetb3.webapp.model.Categorie;
import com.projetb3.webapp.repository.CategorieProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data   //getter et setter
@Service //agit comme l'annot component + specifie que la classe gere le traitement métier
//Classe qui intègre des méthodes CRUD
//agit comme un relais, on peut dire quil agit comme le controller finalement

public class CategorieService {

    @Autowired //permet de réaliser l'injection automatiquement entre les beans de l'application
    private CategorieProxy articleProxy;    //récupère les données de la classe filmProxy

    //READ
    public Categorie getCategorie(final int id) {
        return articleProxy.getCategorie(id);
    }

    public Iterable<Categorie> getAllCategories(){        //iterabale declare que l'objet Categorie sera la cible d'une boucle for each afin de parcourir toutes les instances de Categorie
        return articleProxy.getAllCategories();
    }

    //DELETE
    public void deleteCategorie(final int id){
        articleProxy.deleteCategorie(id);
    }

    //CREATE
    public void createCategorie(Categorie c)  {
        articleProxy.createCategorie(c);
    }
}
