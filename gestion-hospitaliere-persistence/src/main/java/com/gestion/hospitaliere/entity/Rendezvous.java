package com.gestion.hospitaliere.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Rendezvous extends AbstractEntity{

    private Patient patient;
    @OneToMany
    private Set<Docteur> docteurSet;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Set<Docteur> getDocteurSet() {
        return docteurSet;
    }

    public void setDocteurSet(Set<Docteur> docteurSet) {
        this.docteurSet = docteurSet;
    }
}