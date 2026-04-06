package tn.esprit.ds.championnat;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tn.esprit.ds.championnat.entities.*;
import tn.esprit.ds.championnat.repositories.*;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initData(
            SponsorRepository sponsorRepo,
            EquipeRepository equipeRepo,
            ContratRepo contratRepo,
            PiloteRepository piloteRepo
    ) {
        return args -> {

            if (equipeRepo.count() > 0) {
                System.out.println("⏭️ Data already exists, skipping seeder.");
                return;
            }

            // ======================
            // 👥 EQUIPES
            // ======================
            Equipe ferrari = new Equipe();
            ferrari.setLibelle("Ferrari");
            ferrari.setNbPointsTotal(0);
            ferrari.setClassementGeneral(0);

            Equipe redBull = new Equipe();
            redBull.setLibelle("Red Bull");
            redBull.setNbPointsTotal(0);
            redBull.setClassementGeneral(0);

            Equipe mercedes = new Equipe();
            mercedes.setLibelle("Mercedes");
            mercedes.setNbPointsTotal(0);
            mercedes.setClassementGeneral(0);

            Equipe ducati = new Equipe();
            ducati.setLibelle("Ducati");
            ducati.setNbPointsTotal(0);
            ducati.setClassementGeneral(0);

            equipeRepo.save(ferrari);
            equipeRepo.save(redBull);
            equipeRepo.save(mercedes);
            equipeRepo.save(ducati);

            // ======================
            // 🏎️ PILOTES FORMULA1
            // ======================
            Pilote verstappen = new Pilote();
            verstappen.setLibelleP("Max Verstappen");
            verstappen.setNbPointsTotal(65);
            verstappen.setClassementGeneral(0);
            verstappen.setCategorie("FORMULA1");
            verstappen.setEquipe(redBull);

            Pilote hamilton = new Pilote();
            hamilton.setLibelleP("Lewis Hamilton");
            hamilton.setNbPointsTotal(61);
            hamilton.setClassementGeneral(0);
            hamilton.setCategorie("FORMULA1");
            hamilton.setEquipe(ferrari);

            Pilote norris = new Pilote();
            norris.setLibelleP("Lando Norris");
            norris.setNbPointsTotal(48);
            norris.setClassementGeneral(0);
            norris.setCategorie("FORMULA1");
            norris.setEquipe(mercedes);

            // ======================
            // 🏍️ PILOTES MOTOGP
            // ======================
            Pilote bagnaia = new Pilote();
            bagnaia.setLibelleP("Francesco Bagnaia");
            bagnaia.setNbPointsTotal(0);
            bagnaia.setClassementGeneral(0);
            bagnaia.setCategorie("MOTOGP");
            bagnaia.setEquipe(ducati);

            Pilote martin = new Pilote();
            martin.setLibelleP("Jorge Martin");
            martin.setNbPointsTotal(0);
            martin.setClassementGeneral(0);
            martin.setCategorie("MOTOGP");
            martin.setEquipe(ducati);

            Pilote marquez = new Pilote();
            marquez.setLibelleP("Marc Marquez");
            marquez.setNbPointsTotal(0);
            marquez.setClassementGeneral(0);
            marquez.setCategorie("MOTOGP");
            marquez.setEquipe(ducati);

            piloteRepo.save(verstappen);
            piloteRepo.save(hamilton);
            piloteRepo.save(norris);
            piloteRepo.save(bagnaia);
            piloteRepo.save(martin);
            piloteRepo.save(marquez);

            // ======================
            // 💰 SPONSORS
            // ======================
            Sponsor oracle = new Sponsor();
            oracle.setNom("Oracle");
            oracle.setPays("USA");
            oracle.setBudgetAnnuel(70000f);
            oracle.setBloquerContrat(false);
            oracle.setArchived(false);

            Sponsor shell = new Sponsor();
            shell.setNom("Shell");
            shell.setPays("UK");
            shell.setBudgetAnnuel(50000f);
            shell.setBloquerContrat(false);
            shell.setArchived(false);

            Sponsor aramco = new Sponsor();
            aramco.setNom("Aramco");
            aramco.setPays("Saudi Arabia");
            aramco.setBudgetAnnuel(200000f);
            aramco.setBloquerContrat(false);
            aramco.setArchived(false);

            sponsorRepo.save(oracle);
            sponsorRepo.save(shell);
            sponsorRepo.save(aramco);

            // ======================
            // 📄 CONTRATS
            // ======================

            // Active contracts (2026) → will stay active
            Contrat c1 = new Contrat();
            c1.setAnnee("2026");
            c1.setMontant(55000f); // 78.57% of Oracle's 70000 → triggers warning
            c1.setArchived(false);
            c1.setBloquerContrat(false);
            c1.setEquipe(redBull);
            c1.setSponsor(oracle);

            Contrat c2 = new Contrat();
            c2.setAnnee("2026");
            c2.setMontant(20000f);
            c2.setArchived(false);
            c2.setBloquerContrat(false);
            c2.setEquipe(ferrari);
            c2.setSponsor(shell);

            Contrat c3 = new Contrat();
            c3.setAnnee("2026");
            c3.setMontant(80000f);
            c3.setArchived(false);
            c3.setBloquerContrat(false);
            c3.setEquipe(mercedes);
            c3.setSponsor(aramco);

            // Expired contracts (2024) → will be archived by scheduler
            Contrat c4 = new Contrat();
            c4.setAnnee("2024");
            c4.setMontant(15000f);
            c4.setArchived(false);
            c4.setBloquerContrat(false);
            c4.setEquipe(ferrari);
            c4.setSponsor(oracle);

            Contrat c5 = new Contrat();
            c5.setAnnee("2023");
            c5.setMontant(10000f);
            c5.setArchived(false);
            c5.setBloquerContrat(false);
            c5.setEquipe(redBull);
            c5.setSponsor(shell);

            contratRepo.save(c1);
            contratRepo.save(c2);
            contratRepo.save(c3);
            contratRepo.save(c4);
            contratRepo.save(c5);

            System.out.println("✅ TEST DATA INSERTED SUCCESSFULLY");
        };
    }
}