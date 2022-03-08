package com.projetb3.api_watashihouse.controller;

import com.projetb3.api_watashihouse.model.Article;
import com.projetb3.api_watashihouse.model.Commande;
import com.projetb3.api_watashihouse.service.CommandeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/commandes")
public class CommandeController {

    private CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping
    public ResponseEntity<Page<Commande>> getAllCommandesToWatch(
            @RequestParam("page") final Optional<Integer> page,
            @RequestParam("sortBy") final Optional<String> sortBy
    ) {
        Page<Commande> commandeList = commandeService.getAllCommandes(page, sortBy);
        return ResponseEntity.ok(commandeList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Commande> getCommande(@PathVariable("id") final int id) {
        Optional<Commande> commande = commandeService.getCommande(id);
        if (commande.isPresent()) {
            return ResponseEntity.ok(commande.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> createCommande(@RequestBody Commande commande) {
        for (Article article : commande.getArticles()) {
            System.out.println(article.getNom() + " - " + article.getPrix());
        }
        commande.setDateAchat(Commande.now());
        commande.addAll(commande.getArticles());
        int total = getPrixTot(commande.getArticles());
        commande.setPrixTot(total);
        System.out.println(commande.getPrixTot() + " - " + total);
        if(commande.getArticles().isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        commandeService.saveCommande(commande);
        return ResponseEntity.ok().build();
    }

    private int getPrixTot(List<Article> articles) {
        List<Integer> listePrix = new ArrayList<>();
        for (Article article : articles) {
            listePrix.add(article.getPrix());
        }
        Optional<Integer> prixTot = listePrix.stream().reduce(Integer::sum);
        return prixTot.orElse(0);
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
    public ResponseEntity<Void> updateCommande(@PathVariable("id") final int id, @RequestBody Commande commande) {
        Optional<Commande> optCommande = commandeService.getCommande(id);
        if (optCommande.isPresent()) {
            Commande currentCommande = optCommande.get();
            String numero = commande.getNumero();
            String date = commande.getDateAchat();
            int prixTot = commande.getPrixTot();

            if (numero != null) {
                currentCommande.setNumero(numero);
            }
            if (prixTot != 0) {
                currentCommande.setPrixTot(prixTot);
            }
            if (date != null) {
                currentCommande.setDateAchat(date);
            }
            commandeService.saveCommande(currentCommande);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
