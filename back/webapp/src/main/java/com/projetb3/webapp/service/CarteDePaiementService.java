package com.projetb3.webapp.service;
import com.projetb3.webapp.model.CarteDePaiement;
import com.projetb3.webapp.repository.CarteDePaiementProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data   //getter et setter
@Service //agit comme l'annot component + specifie que la classe gere le traitement métier
//Classe qui intègre des méthodes CRUD
//agit comme un relais, on peut dire quil agit comme le controller finalement

public class CarteDePaiementService {

    @Autowired //permet de réaliser l'injection automatiquement entre les beans de l'application
    private CarteDePaiementProxy articleProxy;    //récupère les données de la classe filmProxy

    //READ
    public CarteDePaiement getCarteDePaiement(final int id) {
        return articleProxy.getCarteDePaiement(id);
    }

    public Iterable<CarteDePaiement> getAllCarteDePaiements(){        //iterabale declare que l'objet CarteDePaiement sera la cible d'une boucle for each afin de parcourir toutes les instances de CarteDePaiement
        return articleProxy.getAllCarteDePaiements();
    }

    //DELETE
    public void deleteCarteDePaiement(final int id){
        articleProxy.deleteCarteDePaiement(id);
    }

    //CREATE
    public void createCarteDePaiement(CarteDePaiement c)  {
        articleProxy.createCarteDePaiement(c);
    }
}
