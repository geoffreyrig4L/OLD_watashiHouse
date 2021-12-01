package com.projetb3.api_watashihouse.controller;

import com.projetb3.api_watashihouse.model.Utilisateur;
import com.projetb3.api_watashihouse.service.UtilisateurService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/utilisateurs")
public class UtilisateurController {

    private UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public ResponseEntity<Page<Utilisateur>> getAllUtilisateursToWatch(@RequestParam("page") final Optional<Integer> page, @RequestParam("sortBy") final Optional<String> sortBy) {
        Page<Utilisateur> utilisateurList = utilisateurService.getAllUtilisateurs(page, sortBy);
        return ResponseEntity.ok(utilisateurList);
    }

    /*
        @RequestParam recupere des infos concernant les ressources, tout ce qu'on peut trouver apres le ?, ses infos servent principalement de filtrage
        @PathVariable récupère la ressource directement soit les champs contenu dans notre bdd (id, title, date_released)
     */

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable("id") final int id) {     //PathVariable -> permet de manipuler des variables dans l'URI de la requete mapping
        Optional<Utilisateur> utilisateur = utilisateurService.getUtilisateur(id); //Optional -> encapsule un objet dont la valeur peut être null
        if (utilisateur.isPresent()) {   //si il existe dans la bdd
            return ResponseEntity.ok(utilisateur.get());  //recupere la valeur de utilisateur
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> createUtilisateur(@RequestBody Utilisateur utilisateur) {         // deserialise les JSON dans un langage Java -> regroupe des données séparées dans un meme flux
        // le JSON saisie par l'user dans le body sera donc utiliser pour générer une instance de Utilisateur
        utilisateurService.saveUtilisateur(utilisateur);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable("id") final int id) {  //void sgnifie qu'il n'y a aucun objet dans le body
        Optional<Utilisateur> optUtilisateur = utilisateurService.getUtilisateur(id);  //Optional -> encapsule un objet dont la valeur peut être null

        if (optUtilisateur.isPresent()){
            utilisateurService.deleteUtilisateur(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

/*
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUtilisateur(@PathVariable("id") final int id, @RequestBody Utilisateur utilisateur) { //utilisateur contenu dans le body
        Optional<Utilisateur> optUtilisateur = utilisateurService.getUtilisateur(id);  //Optional -> encapsule un objet dont la valeur peut être null

        if (optUtilisateur.isPresent()) {
            Utilisateur currentUtilisateur = optUtilisateur.get();

            //recupere les variables du utilisateur fourni en parametre pour les manipuler
            String title = utilisateur.getTitle();
            String date = utilisateur.getDate_released();

            if (title != null) {
                currentUtilisateur.setTitle(title);
            }
            if (date != null) {
                currentUtilisateur.setDate_released(date);
            }
            utilisateurService.saveUtilisateur(currentUtilisateur);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
    */

}
