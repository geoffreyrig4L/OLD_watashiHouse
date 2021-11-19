package com.projetb3.webapp.controller;

import com.projetb3.webapp.model.Commande ;
import com.projetb3.webapp.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommandeController {

    @Autowired
    private CommandeService service;

    @GetMapping("/")
    public String home(Model model){
        Iterable<Commande> listCommande = service.getAllCommandes();
        model.addAttribute("commandes",listCommande );
        return "index";
    }
}
