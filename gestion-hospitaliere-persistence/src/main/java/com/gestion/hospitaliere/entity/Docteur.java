package com.gestion.hospitaliere.entity;

import jakarta.persistence.Entity;

@Entity
public class Docteur extends AbstractEntity{
    private String specialite;
    private String role;

    public Docteur() {
    }
    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
