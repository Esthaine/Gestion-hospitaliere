package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Fiche extends AbstractEntity{

    @Column(nullable = false, unique = true)
    private String ficheNumber;

    private String status;

    @OneToMany
    private Set<Departement> departement;


    public Fiche() {
    }

    public String getFicheNumber() {
        return ficheNumber;
    }

    public void setFicheNumber(String ficheNumber) {
        this.ficheNumber = ficheNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Departement> getDepartement() {
        return departement;
    }

    public void setDepartement(Set<Departement> departement) {
        this.departement = departement;
    }
}
