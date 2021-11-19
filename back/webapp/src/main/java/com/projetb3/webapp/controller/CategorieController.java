package com.projetb3.webapp.controller;

import com.projetb3.webapp.model.Categorie;
import com.projetb3.webapp.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CategorieController {
    @Autowired
    private CategorieService service;

    @GetMapping("/")
    public String home(Model model){
        Iterable<Categorie> listCategorie = service.getAllCategories();
        model.addAttribute("categories",listCategorie);
        return "index";
    }
}
