package com.projetb3.webapp.controller;

import com.projetb3.webapp.model.Collection;
import com.projetb3.webapp.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CollectionController {
    @Autowired
    private CollectionService service;

    @GetMapping("/")
    public String home(Model model){
        Iterable<Collection> listCollection = service.getAllCollections();
        model.addAttribute("collections",listCollection);
        return "index";
    }
}
