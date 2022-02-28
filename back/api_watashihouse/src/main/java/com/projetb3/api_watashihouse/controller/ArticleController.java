package com.projetb3.api_watashihouse.controller;

import com.projetb3.api_watashihouse.model.Article;
import com.projetb3.api_watashihouse.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    private ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<Page<Article>> getAllArticlesToWatch(@RequestParam("page") final Optional<Integer> page, @RequestParam("sortBy") final Optional<String> sortBy) {
        Page<Article> articleList = articleService.getAllArticles(page, sortBy);
        return ResponseEntity.ok(articleList);
    }

    /*
        @RequestParam recupere des infos concernant les ressources, tout ce qu'on peut trouver apres le ?, ses infos servent principalement de filtrage
        @PathVariable récupère la ressource directement soit les champs contenu dans notre bdd (id, title, date_released)
     */

    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticle(@PathVariable("id") final int id) {     //PathVariable -> permet de manipuler des variables dans l'URI de la requete mapping
        Optional<Article> article = articleService.getArticle(id); //Optional -> encapsule un objet dont la valeur peut être null
        if (article.isPresent()) {   //si il existe dans la bdd
            return ResponseEntity.ok(article.get());  //recupere la valeur de article
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Void> createArticle(@RequestBody Article article) {         // deserialise les JSON dans un langage Java -> regroupe des données séparées dans un meme flux
        // le JSON saisie par l'user dans le body sera donc utiliser pour générer une instance de Article
        articleService.saveArticle(article);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable("id") final int id) {  //void sgnifie qu'il n'y a aucun objet dans le body
        Optional<Article> optArticle = articleService.getArticle(id);  //Optional -> encapsule un objet dont la valeur peut être null

        if (optArticle.isPresent()){
            articleService.deleteArticle(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateArticle(@PathVariable("id") final int id, @RequestBody Article article) { //article contenu dans le body
        Optional<Article> optArticle = articleService.getArticle(id);  //Optional -> encapsule un objet dont la valeur peut être null

        if (optArticle.isPresent()) {
            Article currentArticle = optArticle.get();

            //recupere les variables du article fourni en parametre pour les manipuler
            String nom = article.getNom();
            String description = article.getDescription();
            String images = article.getImages();
            String couleur = article.getCouleur();
            float prix = article.getPrix();
            int nb_avis = article.getNb_avis();
            float note = article.getNote();
            int stock = article.getStock();

            if (nom != null) {
                currentArticle.setNom(nom);
            }
            if (description != null) {
                currentArticle.setDescription(description);
            }
            if (images != null) {
                currentArticle.setImages(images);
            }
            if (couleur != null) {
                currentArticle.setCouleur(couleur);
            }
            if (prix != 0) {
                currentArticle.setPrix(prix);
            }
            if (nb_avis != 0) {
                currentArticle.setNb_avis(nb_avis);
            }
            if (note != 0) {
                currentArticle.setNote(note);
            }
            if (stock != 0) {
                currentArticle.setNote(stock);
            }
            articleService.saveArticle(currentArticle);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }
}
