package tn.esprit.ds.championnat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.ds.championnat.entities.Contrat;

import java.util.List;

public interface ContratRepo extends JpaRepository<Contrat, Long> {

    @Query("SELECT c FROM Contrat c JOIN FETCH c.equipe JOIN FETCH c.sponsor")
    List<Contrat> findAllFull();

    @Query("SELECT c FROM Contrat c JOIN FETCH c.equipe JOIN FETCH c.sponsor WHERE c.archived = false")
    List<Contrat> findAllWithEquipe();

}