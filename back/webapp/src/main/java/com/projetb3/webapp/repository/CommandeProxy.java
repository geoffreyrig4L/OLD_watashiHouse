package com.projetb3.webapp.repository;

import com.projetb3.webapp.CustomProperties;
import com.projetb3.webapp.model.Commande;
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
public class CommandeProxy {

    @Autowired
    private CustomProperties props;

    public Iterable<Commande> getAllCommandes() {

        String baseApiUrl = props.getApiUrl();
        String getCommandesUrl = baseApiUrl + "/commandes";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Commande>> response = restTemplate.exchange(
                getCommandesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Commande>>() {}
        );

        log.debug("Get Commandes call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Commande getCommande(int id){
        String baseApiUrl = props.getApiUrl();
        String getCommandesUrl = baseApiUrl + "/commandes/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Commande> response = restTemplate.exchange(
                getCommandesUrl, //l'url
                HttpMethod.GET, //le type de requete
                null, //defini un comportement par defaut
                new ParameterizedTypeReference<Commande>() {}
        );

        log.debug("Get Commandes call " + response.getStatusCode().toString()); //affiche des message dans la console

        return response.getBody();  //on récupère notre objet Iterable<Commande> grâce à la méthode getBody() de l’objet Response.
    }

    public Commande createCommande(Commande c){
        String baseApiUrl = props.getApiUrl();
        String createCommandeUrl = baseApiUrl + "/commande";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Commande> request = new HttpEntity<Commande>(c);     //represent une requete HTTP avec un header et un body , sera transmis dans le requestEntity
        ResponseEntity<Commande> response = restTemplate.exchange(
                createCommandeUrl,
                HttpMethod.POST,
                request,    //defini un comportement d'une requete HTTP
                Commande.class);
        log.debug("Create Commande call " +response.getStatusCode().toString());    //affiche des message dans la console

        return response.getBody();
    }

    public void deleteCommande(int id){
        String baseApiUrl = props.getApiUrl();
        String deleteCommandeUrl = baseApiUrl + "/commandes" +id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteCommandeUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Commande call "+response.getStatusCode().toString());
    }

    public Commande updateCommande(Commande c){
        String baseApiurl = props.getApiUrl();
        String updateCommandeUrl =  baseApiurl +"/commandes"+ c.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Commande> request = new HttpEntity<Commande>(c);
        ResponseEntity<Commande> response = restTemplate.exchange(
                updateCommandeUrl,
                HttpMethod.PUT,
                request,
                Commande.class);

        log.debug("Update Commande call " +response.getStatusCode().toString());
        return response.getBody();
    }
}
