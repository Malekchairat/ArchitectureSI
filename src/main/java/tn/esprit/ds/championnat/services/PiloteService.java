package tn.esprit.ds.championnat.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.ds.championnat.entities.Pilote;
import tn.esprit.ds.championnat.repositories.EquipeRepository;
import tn.esprit.ds.championnat.repositories.PiloteRepository;
import tn.esprit.ds.championnat.entities.Equipe;


@Service
public class PiloteService implements IPiloteService {

    @Autowired
    private PiloteRepository piloteRepository;
    @Autowired
    private PiloteRepository pr;

    @Autowired
    private EquipeRepository er;

    @Override
    public String addPilote(Pilote p) {
        piloteRepository.save(p);
        return "Pilote added successfully";
    }


    public String assignPiloteToEquipe(Long piloteId, Long equipeId) {
        Pilote pilote = pr.findById(piloteId).orElse(null);
        Equipe equipe = er.findById(equipeId).orElse(null);
        if (pilote != null && equipe != null) {
            pilote.setEquipe(equipe);
            pr.save(pilote);
            return "Pilote assigné à l'équipe avec succès";
        }
        return "Pilote ou équipe non trouvée";
    }
}
