package com.projetb3.webapp.controller;

import com.projetb3.webapp.model.CarteDePaiement;
import com.projetb3.webapp.service.CarteDePaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarteDePaiementController {

    @Autowired
    private CarteDePaiementService service;

    @GetMapping("/")
    public String home(Model model){
        Iterable<CarteDePaiement> listCarteDePaiement = service.getAllCarteDePaiements();
        model.addAttribute("carte_de_paiement",listCarteDePaiement);
        return "index";
    }
}
