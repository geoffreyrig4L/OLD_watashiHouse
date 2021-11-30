package com.projetb3.webapp.controller;

import com.projetb3.webapp.model.Article;
import com.projetb3.webapp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.stereotype.Controller
public class ControllerWebApp {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategorieService categorieService;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private CarteDePaiementService carteDePaiementService;

    @Autowired
    private UtilisateurService utilisateurService;

    @GetMapping("/")
    public String home(Model model){
        Iterable<Article> listArticle = articleService.getAllArticles();
        model.addAttribute("articles",listArticle);
        return "index";
    }
}
