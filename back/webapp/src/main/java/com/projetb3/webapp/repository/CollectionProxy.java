package com.projetb3.webapp.repository;

import com.projetb3.webapp.CustomProperties;
import com.projetb3.webapp.model.Collection;
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
public class CollectionProxy {

    @Autowired
    private CustomProperties props;

    public Iterable<Collection> getAllCollections() {

        String baseApiUrl = props.getApiUrl();
        String getCollectionsUrl = baseApiUrl + "/collections";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Collection>> response = restTemplate.exchange(
                getCollectionsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Collection>>() {}
        );

        log.debug("Get Collections call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Collection getCollection(int id){
        String baseApiUrl = props.getApiUrl();
        String getCollectionsUrl = baseApiUrl + "/collections/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Collection> response = restTemplate.exchange(
                getCollectionsUrl, //l'url
                HttpMethod.GET, //le type de requete
                null, //defini un comportement par defaut
                new ParameterizedTypeReference<Collection>() {}
        );

        log.debug("Get Collections call " + response.getStatusCode().toString()); //affiche des message dans la console

        return response.getBody();  //on récupère notre objet Iterable<Collection> grâce à la méthode getBody() de l’objet Response.
    }

    public Collection createCollection(Collection c){
        String baseApiUrl = props.getApiUrl();
        String createCollectionUrl = baseApiUrl + "/article";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Collection> request = new HttpEntity<Collection>(c);     //represent une requete HTTP avec un header et un body , sera transmis dans le requestEntity
        ResponseEntity<Collection> response = restTemplate.exchange(
                createCollectionUrl,
                HttpMethod.POST,
                request,    //defini un comportement d'une requete HTTP
                Collection.class);
        log.debug("Create Collection call " +response.getStatusCode().toString());    //affiche des message dans la console

        return response.getBody();
    }

    public void deleteCollection(int id){
        String baseApiUrl = props.getApiUrl();
        String deleteCollectionUrl = baseApiUrl + "/collections" +id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteCollectionUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Collection call "+response.getStatusCode().toString());
    }

    public Collection updateCollection(Collection c){
        String baseApiurl = props.getApiUrl();
        String updateCollectionUrl =  baseApiurl +"/collections"+ c.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Collection> request = new HttpEntity<Collection>(c);
        ResponseEntity<Collection> response = restTemplate.exchange(
                updateCollectionUrl,
                HttpMethod.PUT,
                request,
                Collection.class);

        log.debug("Update Collection call " +response.getStatusCode().toString());
        return response.getBody();
    }
}
