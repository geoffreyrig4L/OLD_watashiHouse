package com.projetb3.webapp.service;

import com.projetb3.webapp.model.Article;
import com.projetb3.webapp.repository.ArticleProxy;
import com.projetb3.webapp.model.Article;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data   //getter et setter
@Service //agit comme l'annot component + specifie que la classe gere le traitement métier
//Classe qui intègre des méthodes CRUD
//agit comme un relais, on peut dire quil agit comme le controller finalement
public class ArticleService {

    @Autowired //permet de réaliser l'injection automatiquement entre les beans de l'application
    private ArticleProxy articleProxy;    //récupère les données de la classe filmProxy

    //READ
    public Article getArticle(final int id) {
        return articleProxy.getArticle(id);
    }

    public Iterable<Article> getAllArticles(){        //iterabale declare que l'objet Article sera la cible d'une boucle for each afin de parcourir toutes les instances de Article
        return articleProxy.getAllArticles();
    }

    //DELETE
    public void deleteArticle(final int id){
        articleProxy.deleteArticle(id);
    }

    //CREATE
    public void createArticle(Article f)  {
        articleProxy.createArticle(f);
    }
}
