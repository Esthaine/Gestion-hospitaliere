package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

@Entity
public class Ville extends AbstractEntity{
    private String nom;

    @ManyToOne
    private Region region;
    private String codePostal;

    public Ville() {
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
}
