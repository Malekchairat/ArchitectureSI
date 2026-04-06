package tn.esprit.ds.championnat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.ds.championnat.entities.Contrat;
import tn.esprit.ds.championnat.entities.Sponsor;
import tn.esprit.ds.championnat.repositories.SponsorRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class SponsorService implements ISponsorService {

    @Autowired
    private SponsorRepository sponsorRepository;

    @Override
    public Sponsor ajouterSponsor(Sponsor sponsor) {
        sponsor.setDateCreation(LocalDate.now());
        sponsor.setArchived(false);
        sponsor.setBloquerContrat(false);
        return sponsorRepository.save(sponsor);
    }

    @Override
    public List<Sponsor> ajouterSponsors(List<Sponsor> sponsors) {
        sponsors.forEach(s -> {
            s.setDateCreation(LocalDate.now());
            s.setArchived(false);
            s.setBloquerContrat(false);
        });
        return sponsorRepository.saveAll(sponsors);
    }

    @Override
    public Sponsor modifierSponsor(Sponsor sponsor) {
        sponsor.setDateDerniereModification(LocalDate.now());
        return sponsorRepository.save(sponsor);
    }

    @Override
    public void supprimerSponsor(Long idSponsor) {
        sponsorRepository.deleteById(idSponsor);
    }

    @Override
    public List<Sponsor> listSponsors() {
        return sponsorRepository.findAll();
    }

    @Override
    public Sponsor recupererSponsor(Long idSponsor) {
        return sponsorRepository.findById(idSponsor).orElse(null);
    }

    @Override
    public Boolean archiverSponsor(Long idSponsor) {
        Sponsor sponsor = sponsorRepository.findById(idSponsor).orElse(null);
        if (sponsor != null) {
            sponsor.setArchived(true);
            sponsorRepository.save(sponsor);
            return true;
        }
        return false;
    }

    // ← ADD THIS
    @Override
    public float pourcentageBudgetAnnuelConsomme(Long idSponsor) {
        Sponsor sponsor = sponsorRepository.findByIdWithContrats(idSponsor).orElse(null);
        if (sponsor == null || sponsor.getBudgetAnnuel() == null || sponsor.getBudgetAnnuel() == 0)
            return 0;

        int currentYear = LocalDate.now().getYear();
        float total = 0;

        for (Contrat c : sponsor.getContrats()) {
            try {
                if (Integer.parseInt(c.getAnnee()) == currentYear) {
                    total += c.getMontant();
                }
            } catch (Exception e) {
                System.out.println("Erreur année contrat ID: " + c.getIdContrat());
            }
        }

        return (total / sponsor.getBudgetAnnuel()) * 100;
    }
}