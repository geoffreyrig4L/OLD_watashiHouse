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
        ResponseEntity<Iterable<Article>> response = restTemplate.exchange(
                getArticlesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Article>>() {}
        );

        log.debug("Get Articles call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Article getArticle(int id){
        String baseApiUrl = props.getApiUrl();
        String getArticlesUrl = baseApiUrl + "/articles/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Article> response = restTemplate.exchange(
                getArticlesUrl, //l'url
                HttpMethod.GET, //le type de requete
                null, //defini un comportement par defaut
                new ParameterizedTypeReference<Article>() {}
        );

        log.debug("Get Articles call " + response.getStatusCode().toString()); //affiche des message dans la console

        return response.getBody();  //on récupère notre objet Iterable<Article> grâce à la méthode getBody() de l’objet Response.
    }

    public Article createArticle(Article a){
        String baseApiUrl = props.getApiUrl();
        String createArticleUrl = baseApiUrl + "/article";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Article> request = new HttpEntity<Article>(a);     //represent une requete HTTP avec un header et un body , sera transmis dans le requestEntity
        ResponseEntity<Article> response = restTemplate.exchange(
                createArticleUrl,
                HttpMethod.POST,
                request,    //defini un comportement d'une requete HTTP
                Article.class);
        log.debug("Create Article call " +response.getStatusCode().toString());    //affiche des message dans la console

        return response.getBody();
    }

    public void deleteArticle(int id){
        String baseApiUrl = props.getApiUrl();
        String deleteArticleUrl = baseApiUrl + "/articles" +id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteArticleUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Article call "+response.getStatusCode().toString());
    }

    public Article updateArticle(Article a){
        String baseApiurl = props.getApiUrl();
        String updateArticleUrl =  baseApiurl +"/articles"+ a.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Article> request = new HttpEntity<Article>(a);
        ResponseEntity<Article> response = restTemplate.exchange(
                updateArticleUrl,
                HttpMethod.PUT,
                request,
                Article.class);

        log.debug("Update Article call " +response.getStatusCode().toString());
        return response.getBody();
    }
}
