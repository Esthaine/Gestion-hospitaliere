package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Decision extends AbstractEntity{

    @ManyToOne
    private Docteur docteur;
    private String description;
    private String type;

    public Decision() {
    }

    public Docteur getDocteur() {
        return docteur;
    }

    public void setDocteur(Docteur docteur) {
        this.docteur = docteur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
