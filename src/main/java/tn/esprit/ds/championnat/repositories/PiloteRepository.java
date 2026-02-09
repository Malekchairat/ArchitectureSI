package tn.esprit.ds.championnat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.ds.championnat.entities.Pilote;

public interface PiloteRepository extends JpaRepository<Pilote, Long> {
}
