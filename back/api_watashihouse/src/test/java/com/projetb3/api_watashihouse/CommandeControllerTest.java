package com.projetb3.api_watashihouse;

import com.projetb3.api_watashihouse.model.Commande;
import com.projetb3.api_watashihouse.repository.CommandeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.ParseException;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD) //force le contexte spring boot a etre recharge apres ce test
//permet ainsi de ne pas creer de conflit apres une suppression   ELLE EST PRESENTE DANS LE H2TestJpaConfig DONT CETTE CLASSE HERITE
public class CommandeControllerTest implements H2TestJpaConfig {

    //pour que les tests fonctionnent : il faut que le getAll verifie les titres de tous sauf du dernier et que le delete supprime le dernier

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public CommandeRepository commandeRepository;

    @BeforeEach  // s execute avant chaque methode de test
    void insertInH2() throws ParseException {
        //les id sont generes automatiquements meme si on les modifies avec @GeneratedValue
        Commande commande = new Commande();
        commande.setNumero("1234567890");
        Date formattedDate = commande.parseDate_livraison("31-11-2021 23:00:00");
        commande.setDate_livraison(formattedDate);
        commande.setPrix_tot(200.0F);
        commande.setLien_vers("https://www.watashi_house.com/commandes/1");
        commandeRepository.save(commande);
        Commande commande2 = new Commande();
        commande2.setNumero("0987654321");
        Date formattedDate2 = commande2.parseDate_livraison("03-12-2021 22:30:00");
        commande2.setDate_livraison(formattedDate2);
        commande2.setPrix_tot(300.0F);
        commande2.setLien_vers("https://www.watashi_house.com/commandes/2");
        commandeRepository.save(commande2);
        Commande commande3 = new Commande();
        commande3.setNumero("111333555999");
        Date formattedDate3 = commande3.parseDate_livraison("05-12-2021 14:45:45");
        commande3.setDate_livraison(formattedDate3);
        commande3.setPrix_tot(400.0F);
        commande3.setLien_vers("https://www.watashi_house.com/commandes/3");
        commandeRepository.save(commande3);
    }

    @Test
    void should_get_all_commandes() throws Exception{
        mockMvc.perform(get("/commandes?page=0&sortBy=id"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].date_livraison",is("31-11-2021 23:00:00")))
                .andExpect(jsonPath("$.content[1].date_livraison",is("03-12-2021 22:30:00")))
                .andExpect(jsonPath("$.content[2].date_livraison",is("05-12-2021 14:45:45")));
    }

    @Test
    void should_get_one_commande() throws Exception{
        mockMvc.perform(get("/commandes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numero",is("1234567890")));
    }

    @Test
    void should_not_get_one_commande() throws Exception{
        mockMvc.perform(get("/commandes/50"))
                .andExpect(status().isNotFound());
    }

    @Test
    void should_put_one_commande() throws Exception{
        mockMvc.perform(put("/commandes/2")
                        .content("{\"id_commande\":2,\"numero\":\"111111111111\"}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(get("/commandes/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numero",is("111111111111")));
    }

    @Test
    void should_not_put_one_commande() throws Exception{
        mockMvc.perform(put("/commandes/50")
                        .content("{\"id_commande\":2,\"numero\":\"}")
                        .contentType(MediaType.APPLICATION_JSON))       //specifie le type de contenu =json
                .andExpect(status().isBadRequest());        //badRequest comme dans la methode update de commandeController
    }

    @Test
    void should_delete_one_commande() throws Exception{
        mockMvc.perform(delete("/commandes/3")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void should_not_delete_one_commande() throws Exception{
        mockMvc.perform(delete("/commandes/50")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
