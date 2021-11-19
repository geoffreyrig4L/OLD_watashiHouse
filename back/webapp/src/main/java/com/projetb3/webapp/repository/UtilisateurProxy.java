package com.projetb3.webapp.repository;

import com.projetb3.webapp.CustomProperties;
import com.projetb3.webapp.model.Utilisateur;
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
public class UtilisateurProxy {

    @Autowired
    private CustomProperties props;

    public Iterable<Utilisateur> getAllUtilisateurs() {

        String baseApiUrl = props.getApiUrl();
        String getUtilisateursUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Utilisateur>> response = restTemplate.exchange(
                getUtilisateursUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Utilisateur>>() {}
        );

        log.debug("Get Utilisateurs call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Utilisateur getUtilisateur(int id){
        String baseApiUrl = props.getApiUrl();
        String getUtilisateursUrl = baseApiUrl + "/utilisateurs/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Utilisateur> response = restTemplate.exchange(
                getUtilisateursUrl, //l'url
                HttpMethod.GET, //le type de requete
                null, //defini un comportement par defaut
                new ParameterizedTypeReference<Utilisateur>() {}
        );

        log.debug("Get Utilisateurs call " + response.getStatusCode().toString()); //affiche des message dans la console

        return response.getBody();  //on récupère notre objet Iterable<Utilisateur> grâce à la méthode getBody() de l’objet Response.
    }

    public Utilisateur createUtilisateur(Utilisateur u){
        String baseApiUrl = props.getApiUrl();
        String createUtilisateurUrl = baseApiUrl + "/article";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Utilisateur> request = new HttpEntity<Utilisateur>(u);     //represent une requete HTTP avec un header et un body , sera transmis dans le requestEntity
        ResponseEntity<Utilisateur> response = restTemplate.exchange(
                createUtilisateurUrl,
                HttpMethod.POST,
                request,    //defini un comportement d'une requete HTTP
                Utilisateur.class);
        log.debug("Create Utilisateur call " +response.getStatusCode().toString());    //affiche des message dans la console

        return response.getBody();
    }

    public void deleteUtilisateur(int id){
        String baseApiUrl = props.getApiUrl();
        String deleteUtilisateurUrl = baseApiUrl + "/utilisateurs" +id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                deleteUtilisateurUrl,
                HttpMethod.DELETE,
                null,
                Void.class);

        log.debug("Delete Utilisateur call "+response.getStatusCode().toString());
    }

    public Utilisateur updateUtilisateur(Utilisateur u){
        String baseApiurl = props.getApiUrl();
        String updateUtilisateurUrl =  baseApiurl +"/utilisateurs"+ u.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Utilisateur> request = new HttpEntity<Utilisateur>(u);
        ResponseEntity<Utilisateur> response = restTemplate.exchange(
                updateUtilisateurUrl,
                HttpMethod.PUT,
                request,
                Utilisateur.class);

        log.debug("Update Utilisateur call " +response.getStatusCode().toString());
        return response.getBody();
    }
}
