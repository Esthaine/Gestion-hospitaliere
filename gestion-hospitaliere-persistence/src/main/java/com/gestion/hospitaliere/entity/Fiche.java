package com.gestion.hospitaliere.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Entity;

import java.util.Date;

@Entity
public class Fiche {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Date dateCreation;
    private Date dateMiseAjour;
    private String status;
    //private Patient patient;
    //private Dapartement departement;


    public Fiche() {
    }

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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateMiseAjour() {
        return dateMiseAjour;
    }

    public void setDateMiseAjour(Date dateMiseAjour) {
        this.dateMiseAjour = dateMiseAjour;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    public Patient getPatient() {
//        return patient;
//    }
//
//    public void setPatient(Patient patient) {
//        this.patient = patient;
//    }
//
//    public Dapartement getDepartement() {
//        return departement;
//    }
//
//    public void setDepartement(Dapartement departement) {
//        this.departement = departement;
//    }


}
