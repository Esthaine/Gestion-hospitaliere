package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class Rendezvous extends AbstractEntity{

    private Date dateHeure;
    @ManyToOne
    private Patient patient;

    @OneToMany
    private Set<Departement> departement;
    private String type;
    private String description;

    public Rendezvous() {
    }

    public Date getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(Date dateHeure) {
        this.dateHeure = dateHeure;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
