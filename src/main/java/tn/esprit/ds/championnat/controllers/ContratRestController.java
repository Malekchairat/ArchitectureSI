package tn.esprit.ds.championnat.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.ds.championnat.entities.Contrat;
import tn.esprit.ds.championnat.services.ContratService;

@RestController
@RequestMapping("/contrat")
public class ContratRestController {

    @Autowired
    private ContratService contratService;

    @PostMapping("/ajouter")
    public Contrat addContrat(@RequestBody Contrat contrat) {
        return contratService.addContrat(contrat);
    }

    @PostMapping("/assign/{sponsorId}/{equipeId}")
    public String assignSponsorToEquipe(@PathVariable Long sponsorId, @PathVariable Long equipeId, @RequestParam Float montant, @RequestParam String annee) {
        return contratService.assignSponsorToEquipe(sponsorId, equipeId, montant, annee);
    }
}