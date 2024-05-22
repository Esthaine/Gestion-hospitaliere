package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Fiche extends AbstractEntity{

    @OneToOne
    private User createdBy;

    @OneToOne
    private Person patient;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(nullable = false, unique = true)
    private String ficheNumber;

    @Enumerated(EnumType.STRING)
    private FicheStatus status;

    @OneToMany(cascade = CascadeType.MERGE)
    private Set<Departement> departement;

    @OneToMany(cascade = CascadeType.MERGE)
    private Set<AntecedentMedical> antecedentMedicals;

    @OneToMany(cascade = CascadeType.MERGE)
    private Set<Medicament> medicaments;

    @OneToMany(cascade = CascadeType.MERGE)
    private Set<Docteur> docteurs;

    @OneToMany(cascade = CascadeType.MERGE)
    private Set<ResultatsExamens> resultatsExamens;

    public Fiche() {
    }

    public String getFicheNumber() {
        return ficheNumber;
    }

    public void setFicheNumber(String ficheNumber) {
        this.ficheNumber = ficheNumber;
    }

    public FicheStatus getStatus() {
        return status;
    }

    public void setStatus(FicheStatus status) {
        this.status = status;
    }

    public Set<Departement> getDepartement() {
        return departement;
    }

    public void setDepartement(Set<Departement> departement) {
        this.departement = departement;
    }

    public Set<AntecedentMedical> getAntecedentMedicals() {
        return antecedentMedicals;
    }

    public void setAntecedentMedicals(Set<AntecedentMedical> antecedentMedicals) {
        this.antecedentMedicals = antecedentMedicals;
    }

    public Set<Medicament> getMedicaments() {
        return medicaments;
    }

    public void setMedicaments(Set<Medicament> medicaments) {
        this.medicaments = medicaments;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public Set<Docteur> getDocteurs() {
        return docteurs;
    }

    public void setDocteurs(Set<Docteur> docteurs) {
        this.docteurs = docteurs;
    }

    public Set<ResultatsExamens> getResultatsExamens() {
        return resultatsExamens;
    }

    public void setResultatsExamens(Set<ResultatsExamens> resultatsExamens) {
        this.resultatsExamens = resultatsExamens;
    }

    public Person getPatient() {
        return patient;
    }

    public void setPatient(Person patient) {
        this.patient = patient;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
