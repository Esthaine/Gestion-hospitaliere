package com.gestion.hospitaliere.entity;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Medicament extends AbstractEntity{

    @Column(unique = true, nullable = false)
    private String nom;
    private String description;
    private String posologie;
    private double prixUnitaire;

    @Enumerated(EnumType.STRING)
    private Stock stock;
    private LocalDate datePeremption;
    private String categorie;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Fiche fiche;

    public Medicament() {
    }

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

    public String getPosologie() {
        return posologie;
    }

    public void setPosologie(String posologie) {
        this.posologie = posologie;
    }


    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public LocalDate getDatePeremption() {
        return datePeremption;
    }

    public void setDatePeremption(LocalDate datePeremption) {
        this.datePeremption = datePeremption;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public Fiche getFiche() {
        return fiche;
    }
}
