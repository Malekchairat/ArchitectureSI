package tn.esprit.ds.championnat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esprit.ds.championnat.entities.*;
import tn.esprit.ds.championnat.repositories.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SchedulerService {

    @Autowired
    private ContratRepo contratRepo;

    @Autowired
    private SponsorRepository sponsorRepository;

    @Autowired
    private PiloteRepository piloteRepository;

    @Autowired
    private SponsorService sponsorService; // ← used for 5.3

    // =========================
    // ✅ 5.1 Every 30 seconds
    // =========================
    @Scheduled(fixedRate = 30000)
    public void archiverContratsExpireesEtAffichageContratsActifsParEquipe() {

        int currentYear = LocalDate.now().getYear();

        List<Contrat> allContrats = contratRepo.findAll();

        for (Contrat c : allContrats) {
            try {
                if (Integer.parseInt(c.getAnnee()) < currentYear) {
                    c.setArchived(true);
                }
            } catch (Exception e) {
                System.out.println("Erreur conversion année pour contrat ID: " + c.getIdContrat());
            }
        }

        contratRepo.saveAll(allContrats);

        List<Contrat> actifs = contratRepo.findAllWithEquipe();

        Map<Equipe, List<Contrat>> map = actifs.stream()
                .collect(Collectors.groupingBy(Contrat::getEquipe));

        System.out.println("===== CONTRATS ACTIFS PAR EQUIPE =====");
        map.forEach((equipe, contrats) -> {
            contrats.forEach(c ->
                    System.out.println("L'équipe " + equipe.getLibelle()
                            + " a un contrat d'un montant de " + c.getMontant()
                            + " avec le sponsor " + c.getSponsor().getNom())
            );
        });
        System.out.println("======================================");
    }

    // =========================
    // ✅ 5.2 31 December 11:15
    // =========================
    @Scheduled(cron = "0 15 11 31 12 *")
    public void updatePointsEtClassement() {

        // Only rank FORMULA1 category pilots
        List<Pilote> formula1Pilotes = piloteRepository.findAll()
                .stream()
                .filter(p -> "FORMULA1".equals(p.getCategorie()))
                .collect(Collectors.toList());

        for (Pilote p : formula1Pilotes) {
            if (p.getNbPointsTotal() == null) {
                p.setNbPointsTotal(0);
            }
        }

        formula1Pilotes.sort((p1, p2) ->
                p2.getNbPointsTotal().compareTo(p1.getNbPointsTotal())
        );

        int rank = 1;
        for (Pilote p : formula1Pilotes) {
            p.setClassementGeneral(rank++);
        }

        piloteRepository.saveAll(formula1Pilotes);

        System.out.println("✅ Classement FORMULA1 mis à jour !");
    }

    // =========================
    // ✅ 5.3 Monday 12:08
    // =========================
    @Scheduled(cron = "0 08 12 * * MON")
    public void verifierBudgetSponsors() {

        List<Sponsor> sponsors = sponsorRepository.findAll();

        System.out.println("===== VERIFICATION BUDGET SPONSORS =====");

        for (Sponsor s : sponsors) {

            // ✅ Calling the required service method as per the PDF
            float pourcentage = sponsorService.pourcentageBudgetAnnuelConsomme(s.getIdSponsor());

            System.out.println("sponsor: " + s.getNom() + " pourcentage : " + pourcentage);

            if (pourcentage > 70 && pourcentage < 100) {
                System.out.println("attention budget presque consommé : " + pourcentage + " % !");
            } else if (pourcentage >= 100) {
                System.out.println("budget dépassé!! vous ne pouvez plus faire de contrats");
                s.setBloquerContrat(true);
                sponsorRepository.save(s);
            }
        }

        System.out.println("========================================");
    }
}