package com.projetb3.webapp.repository;

import com.projetb3.webapp.CustomProperties;
import com.projetb3.webapp.model.CarteDePaiement;
import com.projetb3.webapp.model.CarteDePaiement;
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
public class CarteDePaiementProxy {

    @Autowired
    private CustomProperties props;

    public Iterable<CarteDePaiement> getAllCarteDePaiements() {

        String baseApiUrl = props.getApiUrl();
        String getCarteDePaiementsUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<CarteDePaiement>> response = restTemplate.exchange(
                getCarteDePaiementsUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<CarteDePaiement>>() {}
        );

        log.debug("Get CarteDePaiements call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public CarteDePaiement getCarteDePaiement(int id){
        String baseApiUrl = props.getApiUrl();
        String getCarteDePaiementsUrl = baseApiUrl + "/carte-de-paiement/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<CarteDePaiement> response = restTemplate.exchange(
                getCarteDePaiementsUrl, //l'url
                HttpMethod.GET, //le type de requete
                null, //defini un comportement par defaut
                new ParameterizedTypeReference<CarteDePaiement>() {}
        );

        log.debug("Get CarteDePaiements call " + response.getStatusCode().toString()); //affiche des message dans la console

        return response.getBody();  //on récupère notre objet Iterable<CarteDePaiement> grâce à la méthode getBody() de l’objet Response.
    }

    public CarteDePaiement createCarteDePaiement(CarteDePaiement c){
        String baseApiUrl = props.getApiUrl();
        String createCarteDePaiementUrl = baseApiUrl + "/article";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<CarteDePaiement> request = new HttpEntity<CarteDePaiement>(c);     //represent une requete HTTP avec un header et un body , sera transmis dans le requestEntity
        ResponseEntity<CarteDePaiement> response = restTemplate.exchange(
                createCarteDePaiementUrl,
                HttpMethod.POST,
                request,    //defini un comportement d'une requete HTTP
                CarteDePaiement.class);
        log.debug("Create CarteDePaiement call " +response.getStatusCode().toString());    //affiche des message dans la console

        return response.getBody();
    }

    public void deleteCarteDePaiement(int id){
        String baseApiUrl = props.getApiUrl();
        String deleteCarteDePaiementUrl = baseApiUrl + "/carte-de-paiement" +id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteCarteDePaiementUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete CarteDePaiement call "+response.getStatusCode().toString());
    }

    public CarteDePaiement updateCarteDePaiement(CarteDePaiement c){
        String baseApiurl = props.getApiUrl();
        String updateCarteDePaiementUrl =  baseApiurl +"/carte-de-paiement"+ c.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<CarteDePaiement> request = new HttpEntity<CarteDePaiement>(c);
        ResponseEntity<CarteDePaiement> response = restTemplate.exchange(
                updateCarteDePaiementUrl,
                HttpMethod.PUT,
                request,
                CarteDePaiement.class);

        log.debug("Update CarteDePaiement call " +response.getStatusCode().toString());
        return response.getBody();
    }
}
