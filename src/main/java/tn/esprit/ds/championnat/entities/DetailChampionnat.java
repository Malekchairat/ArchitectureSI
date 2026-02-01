package tn.esprit.ds.championnat.entities;

import jakarta.persistence.*;

@Entity
public class DetailChampionnat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetail;

    private String code;

    private String description;

    // 🔗 Relation with Championnat
    @ManyToOne
    private Championnat championnat;

    // Constructors
    public DetailChampionnat() {}

    public DetailChampionnat(String code, String description, Championnat championnat) {
        this.code = code;
        this.description = description;
        this.championnat = championnat;
    }

    // Getters & Setters
    public Long getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(Long idDetail) {
        this.idDetail = idDetail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Championnat getChampionnat() {
        return championnat;
    }

    public void setChampionnat(Championnat championnat) {
        this.championnat = championnat;
    }
}