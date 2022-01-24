package com.projetb3.webapp.repository;

import com.projetb3.webapp.CustomProperties;
import com.projetb3.webapp.model.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
public class ArticleProxy {

    @Autowired
    private CustomProperties props;

    public Iterable<Article> getAllArticles() {

        String baseApiUrl = props.getApiUrl();
        String getArticlesUrl = baseApiUrl + "/articles";

        RestTemplate restTemplate = new RestTemplate();
        //RestTemplate fait la requête à l’API et convertit le résultat JSON en objet Java
        ResponseEntity<Iterable<Article>> response = restTemplate.exchange(
                getArticlesUrl, //url
                HttpMethod.GET, //Methode HTTP
                null, //laisse un comportement par défaut
                new ParameterizedTypeReference<Iterable<Article>>() {}
                //ParameterizedTypeReference car /articles renvoie un objet Iterable<Article>
        );

        log.debug("Get Articles call " + response.getStatusCode().toString());

        return response.getBody();
    }

//    public Article getArticle(int id){
//        String baseApiUrl = props.getApiUrl();
//        String getArticlesUrl = baseApiUrl + "/articles/" + id;
//
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<Article> response = restTemplate.exchange(
//                getArticlesUrl, //l'url
//                HttpMethod.GET, //le type de requete
//                null, //defini un comportement par defaut
//                new ParameterizedTypeReference<Article>() {}
//        );
//
//        log.debug("Get Articles call " + response.getStatusCode().toString()); //affiche des message dans la console
//
//        return response.getBody();  //on récupère notre objet Iterable<Article> grâce à la méthode getBody() de l’objet Response.
//    }
}
