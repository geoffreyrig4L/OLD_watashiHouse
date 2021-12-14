package com.projetb3.api_watashihouse.service;

import com.projetb3.api_watashihouse.model.Commande;
import com.projetb3.api_watashihouse.repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired //injection automatique des données
    private CommandeRepository commandeRepository;

    public Optional<Commande> getCommande(final int id) {           //Optional -> encapsule un objet dont la valeur peut être null
        return commandeRepository.findById(id);
    }

    public Page<Commande> getAllCommandes (Optional<Integer> page, Optional<String> sortBy) {
        return commandeRepository.findAll(
                PageRequest.of( //Pour créer la page
                        page.orElse(0), //si page est null = on commence à la page 0
                        40,  //taille de la page
                        Sort.Direction.ASC, sortBy.orElse("id_commande") //trier par ordre croissant, avec le param sortBy si il est null = on trie par id
                )
        );
    }

    public void deleteCommande(final int id) {
        commandeRepository.deleteById(id);
    }

    public Commande saveCommande(Commande commande) {           //creer une instance de la table et genere automatiquement l'id
        Commande savedCommande = commandeRepository.save(commande);
        return savedCommande;
    }

}
