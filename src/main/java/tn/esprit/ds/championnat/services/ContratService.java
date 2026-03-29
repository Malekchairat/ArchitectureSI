package tn.esprit.ds.championnat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.ds.championnat.entities.Equipe;
import tn.esprit.ds.championnat.entities.Sponsor;
import tn.esprit.ds.championnat.entities.Contrat;
import tn.esprit.ds.championnat.repositories.ContratRepo;
import tn.esprit.ds.championnat.repositories.EquipeRepository;
import tn.esprit.ds.championnat.repositories.SponsorRepository;

@Service
public class ContratService {

    @Autowired
    private ContratRepo contratRepo;

    @Autowired
    private SponsorRepository sponsorRepo;

    @Autowired
    private EquipeRepository equipeRepo;

    public Contrat addContrat(Contrat contrat) {
        return contratRepo.save(contrat);
    }

    public String assignSponsorToEquipe(Long sponsorId, Long equipeId, Float montant, String annee) {
        Sponsor sponsor = sponsorRepo.findById(sponsorId).orElse(null);
        Equipe equipe = equipeRepo.findById(equipeId).orElse(null);
        if (sponsor != null && equipe != null) {
            Contrat contrat = new Contrat();
            contrat.setSponsor(sponsor);
            contrat.setEquipe(equipe);
            contrat.setMontant(montant);
            contrat.setAnnee(annee);
            contrat.setArchived(false);
            contrat.setBloquerContrat(false);
            contratRepo.save(contrat);
            return "Contrat créé avec succès";
        }
        return "Sponsor ou équipe non trouvée";
    }
}