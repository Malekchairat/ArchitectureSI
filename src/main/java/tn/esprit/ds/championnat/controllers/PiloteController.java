package tn.esprit.ds.championnat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.ds.championnat.entities.Pilote;
import tn.esprit.ds.championnat.services.IPiloteService;

import java.util.List;

@RestController
@RequestMapping("/pilote")
public class PiloteController {

    @Autowired
    private IPiloteService piloteService;

    // POST http://localhost:8081/pilote/add
    @PostMapping("/add")
    public String addPilote(@RequestBody Pilote pilote) {
        return piloteService.addPilote(pilote);
    }

    @PutMapping("/assign/{piloteId}/{equipeId}")
    public String assignPiloteToEquipe(@PathVariable Long piloteId, @PathVariable Long equipeId) {
        return piloteService.assignPiloteToEquipe(piloteId, equipeId);
    }


}