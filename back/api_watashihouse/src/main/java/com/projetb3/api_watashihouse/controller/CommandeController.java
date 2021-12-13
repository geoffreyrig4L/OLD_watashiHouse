package com.projetb3.api_watashihouse.controller;

import com.projetb3.api_watashihouse.model.Commande;
import com.projetb3.api_watashihouse.service.CommandeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/commandes")
public class CommandeController {

    private CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping
    public ResponseEntity<Page<Commande>> getAllCommandesToWatch(@RequestParam("page") final Optional<Integer> page, @RequestParam("sortBy") final Optional<String> sortBy) {
        Page<Commande> commandeList = commandeService.getAllCommandes(page, sortBy);
        return ResponseEntity.ok(commandeList);
    }

    /*
        @RequestParam recupere des infos concernant les ressources, tout ce qu'on peut trouver apres le ?, ses infos servent principalement de filtrage
        @PathVariable récupère la ressource directement soit les champs contenu dans notre bdd (id, title, date_released)
     */

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommande(@PathVariable("id") final int id) {     //PathVariable -> permet de manipuler des variables dans l'URI de la requete mapping
        Optional<Commande> commande = commandeService.getCommande(id); //Optional -> encapsule un objet dont la valeur peut être null
        if (commande.isPresent()) {   //si il existe dans la bdd
            return ResponseEntity.ok(commande.get());  //recupere la valeur de commande
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> createCommande(@RequestBody Commande commande) {         // deserialise les JSON dans un langage Java -> regroupe des données séparées dans un meme flux
        // le JSON saisie par l'user dans le body sera donc utiliser pour générer une instance de Commande
        commandeService.saveCommande(commande);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable("id") final int id) {  //void sgnifie qu'il n'y a aucun objet dans le body
        Optional<Commande> optCommande = commandeService.getCommande(id);  //Optional -> encapsule un objet dont la valeur peut être null

        if (optCommande.isPresent()){
            commandeService.deleteCommande(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCommande(@PathVariable("id") final int id, @RequestBody Commande commande) { //commande contenu dans le body
        Optional<Commande> optCommande = commandeService.getCommande(id);  //Optional -> encapsule un objet dont la valeur peut être null

        if (optCommande.isPresent()) {
            Commande currentCommande = optCommande.get();

            //recupere les variables du commande fourni en parametre pour les manipuler
            String numero = commande.getNumero();
            //
            float prix_tot = commande.getPrix_tot();
            String lien_vers = commande.getLien_vers();

            if (numero != null) {
                currentCommande.setNumero(numero);
            }

            //

            if (prix_tot != 0) {
                currentCommande.setPrix_tot(prix_tot);
            }
            if (lien_vers != null) {
                currentCommande.setLien_vers(lien_vers);
            }
            commandeService.saveCommande(currentCommande);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}
