package tn.esprit.ds.championnat.entities;

import jakarta.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEquipe;

    private String libelle;

    private Integer nbPointsTotal;

    private Integer classementGeneral;

    // 1 Equipe -> * Pilotes
    @OneToMany(
            mappedBy = "equipe",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Pilote> pilotes = new ArrayList<>();

    // Constructors
    public Equipe() {}

    public Equipe(String libelle, Integer nbPointsTotal, Integer classementGeneral) {
        this.libelle = libelle;
        this.nbPointsTotal = nbPointsTotal;
        this.classementGeneral = classementGeneral;
    }

    // Helper methods (VERY good practice)
    public void addPilote(Pilote pilote) {
        pilotes.add(pilote);
        pilote.setEquipe(this);
    }

    public void removePilote(Pilote pilote) {
        pilotes.remove(pilote);
        pilote.setEquipe(null);
    }

    // Getters & Setters
    public Long getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Long idEquipe) {
        this.idEquipe = idEquipe;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public Integer getNbPointsTotal() {
        return nbPointsTotal;
    }

    public void setNbPointsTotal(Integer nbPointsTotal) {
        this.nbPointsTotal = nbPointsTotal;
    }

    public Integer getClassementGeneral() {
        return classementGeneral;
    }

    public void setClassementGeneral(Integer classementGeneral) {
        this.classementGeneral = classementGeneral;
    }

    public List<Pilote> getPilotes() {
        return pilotes;
    }

    public void setPilotes(List<Pilote> pilotes) {
        this.pilotes = pilotes;
    }
}
