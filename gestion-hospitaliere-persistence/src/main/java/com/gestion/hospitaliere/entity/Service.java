package com.gestion.hospitaliere.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Service extends AbstractEntity{

    private String nom;
    private String description;

    @OneToMany
    private List<User> corpMedicalAttacher;

    private LocalDateTime dateAttachement;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getCorpMedicalAttacher() {
        return corpMedicalAttacher;
    }

    public void setCorpMedicalAttacher(List<User> corpMedicalAttacher) {
        this.corpMedicalAttacher = corpMedicalAttacher;
    }

    public LocalDateTime getDateAttachement() {
        return dateAttachement;
    }

    public void setDateAttachement(LocalDateTime dateAttachement) {
        this.dateAttachement = dateAttachement;
    }
}
