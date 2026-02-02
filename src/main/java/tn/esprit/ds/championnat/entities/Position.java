package tn.esprit.ds.championnat.entities;

import jakarta.persistence.*;

@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPosition;

    private Integer classement;

    private Integer nbPoints;

    // * Positions -> 1 Pilote
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pilote")
    private Pilote pilote;

    // * Positions -> 1 Course
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_course")
    private Course course;

    // Constructors
    public Position() {}

    public Position(Integer classement, Integer nbPoints, Pilote pilote, Course course) {
        this.classement = classement;
        this.nbPoints = nbPoints;
        this.pilote = pilote;
        this.course = course;
    }

    // Getters & Setters
    public Long getIdPosition() {
        return idPosition;
    }

    public void setIdPosition(Long idPosition) {
        this.idPosition = idPosition;
    }

    public Integer getClassement() {
        return classement;
    }

    public void setClassement(Integer classement) {
        this.classement = classement;
    }

    public Integer getNbPoints() {
        return nbPoints;
    }

    public void setNbPoints(Integer nbPoints) {
        this.nbPoints = nbPoints;
    }

    public Pilote getPilote() {
        return pilote;
    }

    public void setPilote(Pilote pilote) {
        this.pilote = pilote;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
