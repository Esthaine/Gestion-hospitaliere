package com.gestion.hospitaliere.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Entity;

@Entity
public class Dapartement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String responsable;
    private String equipement;
    private String email;
    private String adresse;
    private String telephone;
    private String specialite;
    private String nombreLits;

    public Dapartement(Long id, String description, String responsable, String equipement, String email, String adresse, String telephone, String specialite, String nombreLits) {
        this.id = id;
        this.description = description;
        this.responsable = responsable;
        this.equipement = equipement;
        this.email = email;
        this.adresse = adresse;
        this.telephone = telephone;
        this.specialite = specialite;
        this.nombreLits = nombreLits;
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

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getEquipement() {
        return equipement;
    }

    public void setEquipement(String equipement) {
        this.equipement = equipement;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getNombreLits() {
        return nombreLits;
    }

    public void setNombreLits(String nombreLits) {
        this.nombreLits = nombreLits;
    }
}
