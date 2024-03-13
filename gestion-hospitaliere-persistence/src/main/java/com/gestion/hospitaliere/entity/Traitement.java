package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Traitement extends AbstractEntity{

    @OneToOne
    private Patient patient;
    @OneToMany
    private Set<Docteur> docteur;
    private String description;
    private Date dateDebut;
    private Date dateFin;
    private String type;
    private String dosage;
    private String commentaires;

    public Traitement() {
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Set<Docteur> getDocteur() {
        return docteur;
    }

    public void setDocteur(Set<Docteur> docteur) {
        this.docteur = docteur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(String commentaires) {
        this.commentaires = commentaires;
    }
}
