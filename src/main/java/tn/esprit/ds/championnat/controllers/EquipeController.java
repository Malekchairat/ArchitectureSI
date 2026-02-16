package tn.esprit.ds.championnat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.ds.championnat.entities.Equipe;
import tn.esprit.ds.championnat.services.IEquipeService;

import java.util.List;

@RestController
@RequestMapping("/equipe")
public class EquipeController {

    @Autowired
    private IEquipeService equipeService;

    // POST http://localhost:8081/equipe/add
    @PostMapping("/add")
    public Equipe addEquipe(@RequestBody Equipe equipe) {
        return equipeService.ajouterEquipe(equipe);
    }


}