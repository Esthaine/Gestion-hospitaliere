package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Region extends AbstractEntity{

    @ManyToOne
    private Pays pays;
    private String code;

    @OneToMany
    private Set<Ville> ville;
    private long population;

    public Region() {
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public Set<Ville> getVille() {
        return ville;
    }

    public void setVille(Set<Ville> ville) {
        this.ville = ville;
    }
}
