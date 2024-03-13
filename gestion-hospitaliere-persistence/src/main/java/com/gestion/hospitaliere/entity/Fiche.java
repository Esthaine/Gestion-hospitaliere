package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Fiche extends AbstractEntity{

    private String status;
    @OneToOne
    private Patient patient;

    @OneToMany
    private Set<Departement> departement;


    public Fiche() {
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

    public Set<Departement> getDepartement() {
        return departement;
    }

    public void setDepartement(Set<Departement> departement) {
        this.departement = departement;
    }
}
