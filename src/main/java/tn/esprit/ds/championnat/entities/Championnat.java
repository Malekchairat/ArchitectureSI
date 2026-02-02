package tn.esprit.ds.championnat.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Championnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChampionnat;

    @Enumerated(EnumType.STRING)
    private Categorie categorie;

    private String libelleC;

    private Integer annee;

    // N–N Championnat ↔ Course (NEW join table)
    @ManyToMany
    @JoinTable(
            name = "championnat_course",
            joinColumns = @JoinColumn(name = "id_championnat"),
            inverseJoinColumns = @JoinColumn(name = "id_course")
    )
    private List<Course> courses;

    // Independent table related only to Championnat
    @OneToMany(mappedBy = "championnat")
    private List<DetailChampionnat> details;

    // Constructors
    public Championnat() {}

    public Championnat(Categorie categorie, String libelleC, Integer annee) {
        this.categorie = categorie;
        this.libelleC = libelleC;
        this.annee = annee;
    }

    // Getters & Setters
    public Long getIdChampionnat() {
        return idChampionnat;
    }

    public void setIdChampionnat(Long idChampionnat) {
        this.idChampionnat = idChampionnat;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public String getLibelleC() {
        return libelleC;
    }

    public void setLibelleC(String libelleC) {
        this.libelleC = libelleC;
    }

    public Integer getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<DetailChampionnat> getDetails() {
        return details;
    }

    public void setDetails(List<DetailChampionnat> details) {
        this.details = details;
    }
}
