package com.projetb3.webapp.service;
import com.projetb3.webapp.model.Collection;
import com.projetb3.webapp.repository.CollectionProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data   //getter et setter
@Service //agit comme l'annot component + specifie que la classe gere le traitement métier
//Classe qui intègre des méthodes CRUD
//agit comme un relais, on peut dire quil agit comme le controller finalement

public class CollectionService {

    @Autowired //permet de réaliser l'injection automatiquement entre les beans de l'application
    private CollectionProxy articleProxy;    //récupère les données de la classe filmProxy

    //READ
    public Collection getCollection(final int id) {
        return articleProxy.getCollection(id);
    }

    public Iterable<Collection> getAllCollections(){        //iterabale declare que l'objet Collection sera la cible d'une boucle for each afin de parcourir toutes les instances de Collection
        return articleProxy.getAllCollections();
    }

    //DELETE
    public void deleteCollection(final int id){
        articleProxy.deleteCollection(id);
    }

    //CREATE
    public void createCollection(Collection c)  {
        articleProxy.createCollection(c);
    }
}
