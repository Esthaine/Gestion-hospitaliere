package com.gestion.hospitaliere.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Docteur extends AbstractEntity{
    private String specialite;
    private String role;

    @ManyToOne( cascade = CascadeType.MERGE)
    @JoinColumn(name = "rendezvous_id", referencedColumnName = "id")
    private Rendezvous rendezvous;

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

    public Rendezvous getRendezvous() {
        return rendezvous;
    }

    public void setRendezvous(Rendezvous rendezvous) {
        this.rendezvous = rendezvous;
    }
}
