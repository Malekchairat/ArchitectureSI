package tn.esprit.ds.championnat.services;

import tn.esprit.ds.championnat.entities.Equipe;

import java.util.List;
import java.util.Optional;

public interface IEquipeService {

    Equipe ajouterEquipe(Equipe equipe);

    List<Equipe> getAllEquipes();

    Optional<Equipe> getEquipeById(Long id);

    void deleteEquipe(Long id);
}