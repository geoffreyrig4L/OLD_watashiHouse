package com.projetb3.webapp.repository;

import com.projetb3.webapp.CustomProperties;
import com.projetb3.webapp.model.Categorie;
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
public class CategorieProxy {

    @Autowired
    private CustomProperties props;

    public Iterable<Categorie> getAllCategories() {

        String baseApiUrl = props.getApiUrl();
        String getCategoriesUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Categorie>> response = restTemplate.exchange(
                getCategoriesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Categorie>>() {}
        );

        log.debug("Get Categories call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Categorie getCategorie(int id){
        String baseApiUrl = props.getApiUrl();
        String getCategoriesUrl = baseApiUrl + "/categories/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Categorie> response = restTemplate.exchange(
                getCategoriesUrl, //l'url
                HttpMethod.GET, //le type de requete
                null, //defini un comportement par defaut
                new ParameterizedTypeReference<Categorie>() {}
        );

        log.debug("Get Categories call " + response.getStatusCode().toString()); //affiche des message dans la console

        return response.getBody();  //on récupère notre objet Iterable<Categorie> grâce à la méthode getBody() de l’objet Response.
    }

    public Categorie createCategorie(Categorie c){
        String baseApiUrl = props.getApiUrl();
        String createCategorieUrl = baseApiUrl + "/article";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Categorie> request = new HttpEntity<Categorie>(c);     //represent une requete HTTP avec un header et un body , sera transmis dans le requestEntity
        ResponseEntity<Categorie> response = restTemplate.exchange(
                createCategorieUrl,
                HttpMethod.POST,
                request,    //defini un comportement d'une requete HTTP
                Categorie.class);
        log.debug("Create Categorie call " +response.getStatusCode().toString());    //affiche des message dans la console

        return response.getBody();
    }

    public void deleteCategorie(int id){
        String baseApiUrl = props.getApiUrl();
        String deleteCategorieUrl = baseApiUrl + "/categories" +id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteCategorieUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Categorie call "+response.getStatusCode().toString());
    }

    public Categorie updateCategorie(Categorie c){
        String baseApiurl = props.getApiUrl();
        String updateCategorieUrl =  baseApiurl +"/categories"+ c.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Categorie> request = new HttpEntity<Categorie>(c);
        ResponseEntity<Categorie> response = restTemplate.exchange(
                updateCategorieUrl,
                HttpMethod.PUT,
                request,
                Categorie.class);

        log.debug("Update Categorie call " +response.getStatusCode().toString());
        return response.getBody();
    }
}
