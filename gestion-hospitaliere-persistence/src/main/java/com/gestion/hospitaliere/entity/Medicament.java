package com.gestion.hospitaliere.entity;


import jakarta.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Medicament extends AbstractEntity{

    private String nom;
    private String description;
    private String posologie;
    private int stock;
    private double prixUnitaire;
    private LocalDateTime datePeremption;
    private String categorie;

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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public LocalDateTime getDatePeremption() {
        return datePeremption;
    }

    public void setDatePeremption(LocalDateTime datePeremption) {
        this.datePeremption = datePeremption;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
}
