package com.projetb3.webapp.controller;

import com.projetb3.webapp.model.Utilisateur;
import com.projetb3.webapp.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UtilisateurController {
    @Autowired
    private UtilisateurService service;

    @GetMapping("/")
    public String home(Model model){
        Iterable<Utilisateur> listUtilisateur = service.getAllUtilisateurs();
        model.addAttribute("utilisateurs",listUtilisateur);
        return "index";
    }
}
