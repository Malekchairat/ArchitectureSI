package tn.esprit.ds.championnat.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.ds.championnat.entities.Sponsor;
import tn.esprit.ds.championnat.services.ISponsorService;

import java.util.List;

@RestController
@RequestMapping("/sponsor")
public class SponsorController {

    @Autowired
    private ISponsorService sponsorService;

    // Add single sponsor
    @PostMapping("/add")
    public Sponsor addSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.ajouterSponsor(sponsor);
    }

    // Add multiple sponsors
    @PostMapping("/addAll")
    public List<Sponsor> addSponsors(@RequestBody List<Sponsor> sponsors) {
        return sponsorService.ajouterSponsors(sponsors);
    }

    // Get all sponsors
    @GetMapping("/all")
    public List<Sponsor> getAllSponsors() {
        return sponsorService.listSponsors();
    }

    // Get sponsor by ID
    @GetMapping("/{id}")
    public Sponsor getSponsor(@PathVariable("id") Long id) {
        return sponsorService.recupererSponsor(id);
    }

    // Update sponsor
    @PutMapping("/update")
    public Sponsor updateSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.modifierSponsor(sponsor);
    }

    // Delete sponsor
    @DeleteMapping("/delete/{id}")
    public void deleteSponsor(@PathVariable("id") Long id) {
        sponsorService.supprimerSponsor(id);
    }

    // Archive sponsor
    @PutMapping("/archive/{id}")
    public Boolean archiveSponsor(@PathVariable("id") Long id) {
        return sponsorService.archiverSponsor(id);
    }




}
