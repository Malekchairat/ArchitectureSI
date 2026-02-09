package tn.esprit.ds.championnat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.ds.championnat.entities.Equipe;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
}
