package tn.esprit.ds.championnat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.ds.championnat.entities.Sponsor;

public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
}
