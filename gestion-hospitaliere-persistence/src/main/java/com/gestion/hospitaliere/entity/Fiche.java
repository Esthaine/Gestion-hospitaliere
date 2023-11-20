package com.gestion.hospitaliere.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Fiche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate dateCreation;
    private LocalDate dateMiseAjour;
    private String status;
    private Patient patient;
    private Dapartement departement;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public LocalDate getDateMiseAjour() {
        return dateMiseAjour;
    }

    public void setDateMiseAjour(LocalDate dateMiseAjour) {
        this.dateMiseAjour = dateMiseAjour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Dapartement getDepartement() {
        return departement;
    }

    public void setDepartement(Dapartement departement) {
        this.departement = departement;
    }

    public Fiche(Long id, String description, LocalDate dateCreation, LocalDate dateMiseAjour, String status, Patient patient, Dapartement departement) {
        this.id = id;
        this.description = description;
        this.dateCreation = dateCreation;
        this.dateMiseAjour = dateMiseAjour;
        this.status = status;
        this.patient = patient;
        this.departement = departement;
    }
}
