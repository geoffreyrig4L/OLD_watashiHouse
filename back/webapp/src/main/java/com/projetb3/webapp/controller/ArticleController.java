package com.projetb3.webapp.controller;

import com.projetb3.webapp.model.Article;
import com.projetb3.webapp.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService service;

    @GetMapping("/")
    public String home(Model model){
        Iterable<Article> listArticle = service.getAllArticles();
        model.addAttribute("articles",listArticle);
        return "index";
    }
}
