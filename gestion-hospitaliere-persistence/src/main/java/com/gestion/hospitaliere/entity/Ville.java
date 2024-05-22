package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Ville extends AbstractEntity{
    @Column(unique = true, nullable = false)
    private String nom;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "region_id", referencedColumnName = "id")
    private Region region;

    @Column(unique = true, nullable = false)
    private String codePostal;

    @OneToMany(cascade =  CascadeType.MERGE)
    private Set<Person> persons = new HashSet<>();

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

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
