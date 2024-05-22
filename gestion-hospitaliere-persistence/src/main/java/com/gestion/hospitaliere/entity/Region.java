package com.gestion.hospitaliere.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Region extends AbstractEntity{

    @Column(unique = true, nullable = false)
    private String regionName;

    @ManyToOne(cascade =  CascadeType.MERGE)
    @JoinColumn(name = "pays_id", referencedColumnName = "id")
    private Pays pays;

    @Column(unique = true)
    private String code;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Ville> ville;

    @OneToMany(cascade =  CascadeType.MERGE)
    private Set<Person> persons = new HashSet<>();

    public Region() {
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
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

    public Set<Ville> getVille() {
        return ville;
    }

    public void setVille(Set<Ville> ville) {
        this.ville = ville;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
