package tn.esprit.ds.championnat.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;


@Entity
public class Sponsor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSponsor;

    private String nom;

    private String pays;

    private Float budgetAnnuel;

    private Boolean bloquerContrat;

    // 1 Sponsor -> * Contrats
    @OneToMany(
            mappedBy = "sponsor",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Contrat> contrats = new ArrayList<>();

    // Constructors
    public Sponsor() {}

    public Sponsor(String nom, String pays, Float budgetAnnuel, Boolean bloquerContrat) {
        this.nom = nom;
        this.pays = pays;
        this.budgetAnnuel = budgetAnnuel;
        this.bloquerContrat = bloquerContrat;
    }

    // Helper methods
    public void addContrat(Contrat contrat) {
        contrats.add(contrat);
        contrat.setSponsor(this);
    }

    public void removeContrat(Contrat contrat) {
        contrats.remove(contrat);
        contrat.setSponsor(null);
    }

    // Getters & Setters
    public Long getIdSponsor() {
        return idSponsor;
    }

    public void setIdSponsor(Long idSponsor) {
        this.idSponsor = idSponsor;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Float getBudgetAnnuel() {
        return budgetAnnuel;
    }

    public void setBudgetAnnuel(Float budgetAnnuel) {
        this.budgetAnnuel = budgetAnnuel;
    }

    public Boolean getBloquerContrat() {
        return bloquerContrat;
    }

    public void setBloquerContrat(Boolean bloquerContrat) {
        this.bloquerContrat = bloquerContrat;
    }

    public List<Contrat> getContrats() {
        return contrats;
    }

    public void setContrats(List<Contrat> contrats) {
        this.contrats = contrats;
    }
    private Boolean archived;
    private LocalDate dateCreation;
    private LocalDate dateDerniereModification;

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateDerniereModification() {
        return dateDerniereModification;
    }

    public void setDateDerniereModification(LocalDate dateDerniereModification) {
        this.dateDerniereModification = dateDerniereModification;
    }


}
