package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Pays extends AbstractEntity{

    @Column(unique = true, nullable = false)
    private String nom;
    @Column(unique = true, nullable = false)
    private String codeIso;

    @OneToMany(cascade =  CascadeType.MERGE)
    private Set<Region> regions = new HashSet<>();

    public Pays() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodeIso() {
        return codeIso;
    }

    public void setCodeIso(String codeIso) {
        this.codeIso = codeIso;
    }

    public Set<Region> getRegions() {
        return regions;
    }

    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }
}
