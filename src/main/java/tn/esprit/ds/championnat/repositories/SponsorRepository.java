package tn.esprit.ds.championnat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.ds.championnat.entities.Sponsor;

import java.util.Optional;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {

    @Query("SELECT s FROM Sponsor s LEFT JOIN FETCH s.contrats WHERE s.idSponsor = :id")
    Optional<Sponsor> findByIdWithContrats(Long id);
}