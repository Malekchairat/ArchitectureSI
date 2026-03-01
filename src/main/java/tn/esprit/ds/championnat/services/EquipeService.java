package tn.esprit.ds.championnat.services;

import org.springframework.stereotype.Service;
import tn.esprit.ds.championnat.entities.Equipe;
import tn.esprit.ds.championnat.repositories.EquipeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EquipeService implements IEquipeService {

    private final EquipeRepository equipeRepository;

    // ✅ Constructor Injection (Best Practice)
    public EquipeService(EquipeRepository equipeRepository) {
        this.equipeRepository = equipeRepository;
    }

    @Override
    public Equipe ajouterEquipe(Equipe equipe) {
        return equipeRepository.save(equipe);
    }

    @Override
    public List<Equipe> getAllEquipes() {
        return equipeRepository.findAll();
    }

    @Override
    public Optional<Equipe> getEquipeById(Long id) {
        return equipeRepository.findById(id);
    }

    @Override
    public void deleteEquipe(Long id) {
        equipeRepository.deleteById(id);
    }
}